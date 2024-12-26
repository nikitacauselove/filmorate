package com.example.application.service.impl;

import com.example.api.dto.FilmDto;
import com.example.api.dto.enums.EventType;
import com.example.api.dto.enums.Operation;
import com.example.api.dto.enums.SortBy;
import com.example.application.mapper.DirectorMapper;
import com.example.application.mapper.FilmMapper;
import com.example.application.mapper.GenreMapper;
import com.example.application.repository.EventRepository;
import com.example.application.repository.GenreRepository;
import com.example.application.repository.MpaRepository;
import com.example.application.repository.UserRepository;
import com.example.application.repository.entity.Event;
import com.example.application.repository.entity.Genre;
import com.example.application.repository.entity.Director;
import com.example.application.repository.entity.Film;
import com.example.application.repository.entity.Mpa;
import com.example.application.repository.entity.User;
import com.example.application.repository.DirectorRepository;
import com.example.application.repository.FilmRepository;
import com.example.application.repository.specification.FilmSpecification;
import com.example.application.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final DirectorMapper directorMapper;
    private final DirectorRepository directorRepository;
    private final EventRepository eventRepository;
    private final FilmMapper filmMapper;
    private final FilmRepository filmRepository;
    private final FilmSpecification filmSpecification;
    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;
    private final MpaRepository mpaRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Film create(FilmDto filmDto) {
        Mpa mpa = mpaRepository.findById(filmDto.mpa().id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Рейтинг Американской киноассоциации с указанным идентификатором не найден"));
        Iterable<Long> genreIds = genreMapper.toIds(filmDto.genres());
        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(genreIds));
        Iterable<Long> directorIds = directorMapper.toIds(filmDto.directors());
        Set<Director> directors = new HashSet<>(directorRepository.findAllById(directorIds));
        Film film = filmMapper.toFilm(filmDto, mpa, genres, directors);

        return filmRepository.save(film);
    }

    @Override
    @Transactional
    public Film update(FilmDto filmDto) {
        Mpa mpa = mpaRepository.findById(filmDto.mpa().id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Рейтинг Американской киноассоциации с указанным идентификатором не найден"));
        Iterable<Long> genreIds = genreMapper.toIds(filmDto.genres());
        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(genreIds));
        Iterable<Long> directorIds = directorMapper.toIds(filmDto.directors());
        Set<Director> directors = new HashSet<>(directorRepository.findAllById(directorIds));
        Film film = findById(filmDto.id());

        return filmMapper.updateFilm(filmDto, mpa, genres, directors, film);
    }

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Фильм с указанным идентификатором не найден"));
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll(Sort.by(Sort.Direction.ASC, Film.Fields.id));
    }

    @Override
    public List<Film> findAllByDirectorId(Long directorId, SortBy sortBy) {
        if (!directorRepository.existsById(directorId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Режиссер с указанным идентификатором не найден");
        }
        return filmRepository.findAllByDirectors_Id(directorId, Sort.by(sortBy.getCriteria()));
    }

    @Override
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addOrDeleteLike(Long id, Long userId, Operation operation) {
        Film film = findById(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден"));
        boolean liked = film.getLikingUsers().contains(user);

        switch (operation) {
            case ADD -> {
                if (!liked) {
                    film.getLikingUsers().add(user);
                    film.setLikesAmount(film.getLikesAmount() + 1);
                }
            }
            case REMOVE -> {
                if (liked) {
                    film.getLikingUsers().remove(user);
                    film.setLikesAmount(film.getLikesAmount() - 1);
                }
            }
        }
        eventRepository.save(Event.builder()
                .userId(userId)
                .eventType(EventType.LIKE)
                .operation(operation)
                .entityId(id)
                .build());
    }

    @Override
    public List<Film> findCommon(Long userId, Long friendId) {
        return filmRepository.findCommon(userId, friendId);
    }

    @Override
    public List<Film> findPopular(Integer count, Long genreId, Integer year) {
        Specification<Film> specification = filmSpecification.findPopular(genreId, year);
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, Film.Fields.likesAmount));

        return filmRepository.findAll(specification, pageable).getContent();
    }

    @Override
    public List<Film> search(String query, List<String> by) {
        Specification<Film> specification = filmSpecification.search(query, by);
        List<Sort.Order> orders = List.of(Sort.Order.desc(Film.Fields.likesAmount), Sort.Order.asc(Film.Fields.id));

        return filmRepository.findAll(specification, Sort.by(orders));
    }
}
