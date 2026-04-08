package com.example.application.service.impl;

import com.example.api.model.By;
import com.example.api.model.FilmDto;
import com.example.application.entity.Director;
import com.example.application.entity.Event;
import com.example.application.entity.EventType;
import com.example.application.entity.Film;
import com.example.application.entity.Genre;
import com.example.application.entity.Mpa;
import com.example.application.entity.Operation;
import com.example.application.entity.SortBy;
import com.example.application.entity.User;
import com.example.application.exception.NotFoundException;
import com.example.application.mapper.DirectorMapper;
import com.example.application.mapper.FilmMapper;
import com.example.application.mapper.GenreMapper;
import com.example.application.repository.FilmRepository;
import com.example.application.repository.specification.FilmSpecification;
import com.example.application.service.DirectorService;
import com.example.application.service.EventService;
import com.example.application.service.FilmService;
import com.example.application.service.GenreService;
import com.example.application.service.MpaService;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private static final Sort SORT_BY_ASCENDING_ID = Sort.by(Sort.Direction.ASC, Film.Fields.id);
    private static final Sort SORT_BY_DESCENDING_LIKES_AMOUNT = Sort.by(Sort.Direction.DESC, Film.Fields.likesAmount);

    private final DirectorMapper directorMapper;
    private final DirectorService directorService;
    private final EventService eventService;
    private final FilmMapper filmMapper;
    private final FilmRepository filmRepository;
    private final FilmSpecification filmSpecification;
    private final GenreMapper genreMapper;
    private final GenreService genreService;
    private final MpaService mpaService;
    @Lazy
    private final UserService userService;

    @Override
    @Transactional
    public Film create(FilmDto filmDto) {
        Mpa mpa = mpaService.findById(filmDto.mpa().id());
        List<Genre> genres = genreService.findAllById(genreMapper.toIds(filmDto.genres()));
        List<Director> directors = directorService.findAllById(directorMapper.toIds(filmDto.directors()));
        Film film = filmMapper.toEntity(filmDto, mpa, Set.copyOf(genres), Set.copyOf(directors), Collections.emptySet());

        return filmRepository.save(film);
    }

    @Override
    @Transactional
    public Film update(FilmDto filmDto) {
        Mpa mpa = mpaService.findById(filmDto.mpa().id());
        List<Genre> genres = genreService.findAllById(genreMapper.toIds(filmDto.genres()));
        List<Director> directors = directorService.findAllById(directorMapper.toIds(filmDto.directors()));
        Film film = findById(filmDto.id());

        return filmMapper.updateEntity(filmDto, mpa, Set.copyOf(genres), Set.copyOf(directors), film);
    }

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FilmRepository.NOT_FOUND));
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll(SORT_BY_ASCENDING_ID);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> findAllByDirectorId(Long directorId, SortBy sortBy) {
        Director director = directorService.findById(directorId);

        return filmRepository.findAllByDirectors_Id(director.getId(), Sort.by(sortBy.getProperty()));
    }

    @Override
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addLike(Long id, Long userId) {
        Film film = findById(id);
        User user = userService.findById(userId);

        if (!film.getLikingUsers().contains(user)) {
            film.getLikingUsers().add(user);
            film.setLikesAmount(film.getLikesAmount() + 1);
        }
        eventService.create(Event.builder()
                .userId(userId)
                .eventType(EventType.LIKE)
                .operation(Operation.ADD)
                .entityId(id)
                .build());
    }

    @Override
    @Transactional
    public void deleteLike(Long id, Long userId) {
        Film film = findById(id);
        User user = userService.findById(userId);

        if (film.getLikingUsers().contains(user)) {
            film.getLikingUsers().remove(user);
            film.setLikesAmount(film.getLikesAmount() - 1);
        }
        eventService.create(Event.builder()
                .userId(userId)
                .eventType(EventType.LIKE)
                .operation(Operation.REMOVE)
                .entityId(id)
                .build());
    }

    @Override
    public void decreaseLikesAmount(Long userId) {
        filmRepository.decreaseLikesAmount(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> findCommon(Long userId, Long friendId) {
        List<Long> ids = filmRepository.findCommon(userId, friendId);

        return filmRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> findPopular(Integer count, Long genreId, Integer year) {
        Specification<Film> specification = filmSpecification.byGenres(genreId).and(filmSpecification.byReleaseDate(year));
        Pageable pageable = PageRequest.of(0, count, SORT_BY_DESCENDING_LIKES_AMOUNT);
        List<Long> ids = filmRepository.findAll(specification, pageable).getContent().stream()
                .map(Film::getId)
                .toList();

        return filmRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> findRecommendations(Long userId) {
        List<Long> userIds = userService.findRelevant(userId);
        List<Long> ids = filmRepository.findRecommendations(userId, userIds);

        return filmRepository.findAllById(ids);
    }

    @Override
    public List<Film> search(String query, List<By> by) {
        Specification<Film> specification = filmSpecification.byName(query, by).or(filmSpecification.byDirectors(query, by));
        Sort sort = SORT_BY_DESCENDING_LIKES_AMOUNT.and(SORT_BY_ASCENDING_ID);

        return filmRepository.findAll(specification, sort);
    }
}
