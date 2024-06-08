package com.example.backend.service.impl;

import com.example.backend.repository.entity.Genre;
import com.example.backend.repository.entity.Director;
import com.example.backend.repository.entity.Event;
import com.example.backend.repository.entity.Film;
import com.example.backend.repository.entity.User;
import com.example.backend.repository.DirectorRepository;
import com.example.backend.repository.FilmRepository;
import com.example.backend.service.EventService;
import com.example.backend.service.FilmService;
import com.example.backend.service.GenreService;
import com.example.backend.service.UserService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final EventService eventService;
    private final UserService userService;
    private final GenreService genreService;
    private final DirectorRepository directorRepository;
    private final FilmRepository filmRepository;

    @Override
    @Transactional
    public Film create(Film film) {
        filmRepository.save(film);

        return findById(film.getId());
    }

    @Override
    @Transactional
    public Film update(Film film) {
        filmRepository.save(film);

        Film result = findById(film.getId());

        System.out.println("----------------------------------");
        result.getGenres().forEach(genre -> System.out.println(genre.getId()));
        System.out.println("----------------------------------");
        return result;
    }

    @Override
    @Transactional
    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Фильм с указанным идентификатором не найден."));
    }

    @Override
    @Transactional
    public List<Film> findAll() {
        return filmRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    @Transactional
    public List<Film> findAllByDirectorId(Long directorId, Film.SortBy sortBy) {
        if (!directorRepository.existsById(directorId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Режиссер с указанным идентификатором не найден.");
        }
        return filmRepository.findAllByDirectors_Id(directorId, Sort.by(sortBy.getProperty()));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addLike(Long id, Long userId) {
        Film film = findById(id);
        User user = userService.findById(userId);

        film.getLikingUsers().add(user);
        film.setLikesAmount(film.getLikesAmount() + 1);
        eventService.create(new Event(null, LocalDateTime.now(), userId, Event.EventType.LIKE, Event.Operation.ADD, id));
    }

    @Override
    @Transactional
    public void deleteLike(Long id, Long userId) {
        Film film = findById(id);
        User user = userService.findById(userId);

        film.getLikingUsers().remove(user);
        film.setLikesAmount(film.getLikesAmount() - 1);

        eventService.create(new Event(null, LocalDateTime.now(), userId, Event.EventType.LIKE, Event.Operation.REMOVE, id));
    }

    @Override
    public List<Film> findCommon(Long userId, Long friendId) {
        return filmRepository.findCommon(userId, friendId);
    }

    @Override
    public List<Film> findPopular(Integer count, Long genreId, Integer year) {
        return filmRepository.findAll(createFindPopularSpecification(genreId, year), PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "likesAmount"))).getContent();
    }

    @Override
    public List<Film> search(String query, List<String> by) {
        List<Sort.Order> orders = List.of(Sort.Order.asc("likesAmount"), Sort.Order.asc("id"));

        return filmRepository.findAll(createSearchSpecification(query, by), Sort.by(orders));
    }

    private Specification<Film> createFindPopularSpecification(Long genreId, Integer year) {
        return ((root, query, criteriaBuilder) -> {
            Collection<Predicate> predicates = new ArrayList<>();

            if (genreId != null) {
                Genre genre = genreService.findById(genreId);

                predicates.add(criteriaBuilder.isMember(genre, root.get("genres")));
            }
            if (year != null) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("date_part", Integer.class, criteriaBuilder.literal("year"), root.get("releaseDate")), year));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    private Specification<Film> createSearchSpecification(String query, List<String> by) {
        return ((root, query1, criteriaBuilder) -> {
            Collection<Predicate> predicates = new ArrayList<>();

            if (by.contains("director")) {
                Optional<Director> director = directorRepository.findByNameContainingIgnoreCase(query);

                director.ifPresent(value -> predicates.add(criteriaBuilder.isMember(value, root.get("directors"))));

            }
            if (by.contains("title")) {
                Predicate predicate = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), criteriaBuilder.lower(criteriaBuilder.literal("%"+ query +"%"))));
                predicates.add(predicate);
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        });
    }
}
