package com.example.application.persistence.impl;

import com.example.application.domain.By;
import com.example.application.domain.EventType;
import com.example.application.domain.Film;
import com.example.application.domain.Operation;
import com.example.application.domain.SortBy;
import com.example.application.exception.NotFoundException;
import com.example.application.persistence.DirectorPersistenceService;
import com.example.application.persistence.EventPersistenceService;
import com.example.application.persistence.FilmPersistenceService;
import com.example.application.persistence.mapper.DirectorEntityMapper;
import com.example.application.persistence.mapper.FilmEntityMapper;
import com.example.application.persistence.mapper.GenreEntityMapper;
import com.example.application.persistence.model.DirectorEntity;
import com.example.application.persistence.model.FilmEntity;
import com.example.application.persistence.model.GenreEntity;
import com.example.application.persistence.model.MpaEntity;
import com.example.application.persistence.model.UserEntity;
import com.example.application.persistence.repository.DirectorRepository;
import com.example.application.persistence.repository.FilmRepository;
import com.example.application.persistence.repository.GenreRepository;
import com.example.application.persistence.repository.MpaRepository;
import com.example.application.persistence.repository.UserRepository;
import com.example.application.persistence.specification.FilmSpecification;
import lombok.RequiredArgsConstructor;
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
public class FilmPersistenceServiceImpl implements FilmPersistenceService {

    private static final Sort SORT_BY_ASCENDING_ID = Sort.by(Sort.Direction.ASC, FilmEntity.Fields.id);
    private static final Sort SORT_BY_DESCENDING_LIKES_AMOUNT = Sort.by(Sort.Direction.DESC, FilmEntity.Fields.likesAmount);

    private final DirectorEntityMapper directorEntityMapper;
    private final DirectorPersistenceService directorPersistenceService;
    private final EventPersistenceService eventPersistenceService;
    private final FilmRepository filmRepository;
    private final FilmSpecification filmSpecification;
    private final GenreEntityMapper genreEntityMapper;
    private final UserRepository userRepository;
    private final FilmEntityMapper filmEntityMapper;
    private final MpaRepository mpaRepository;
    private final GenreRepository genreRepository;
    private final DirectorRepository directorRepository;

    @Override
    @Transactional
    public Film create(Film film) {
        MpaEntity mpa = mpaRepository.findById(film.mpa().id())
                .orElseThrow(() -> new NotFoundException(MpaRepository.NOT_FOUND));
        List<GenreEntity> genres = genreRepository.findAllById(genreEntityMapper.toIds(film.genres()));
        List<DirectorEntity> directors = directorRepository.findAllById(directorEntityMapper.toIds(film.directors()));
        FilmEntity filmEntity = filmEntityMapper.toEntity(film, mpa, Set.copyOf(genres), Set.copyOf(directors), Collections.emptySet());

        return filmEntityMapper.toDomain(filmRepository.save(filmEntity));
    }

    @Override
    @Transactional
    public Film update(Film film) {
        MpaEntity mpa = mpaRepository.findById(film.mpa().id())
                .orElseThrow(() -> new NotFoundException(MpaRepository.NOT_FOUND));
        List<GenreEntity> genres = genreRepository.findAllById(genreEntityMapper.toIds(film.genres()));
        List<DirectorEntity> directors = directorRepository.findAllById(directorEntityMapper.toIds(film.directors()));
        FilmEntity filmEntity = filmRepository.findById(film.id())
                .orElseThrow(() -> new NotFoundException(FilmRepository.NOT_FOUND));
        FilmEntity updatedEntity = filmEntityMapper.updateEntity(film, mpa, Set.copyOf(genres), Set.copyOf(directors), filmEntity);

        return filmEntityMapper.toDomain(updatedEntity);
    }

    @Override
    public Film findById(Long id) {
        FilmEntity filmEntity = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FilmRepository.NOT_FOUND));

        return filmEntityMapper.toDomain(filmEntity);
    }

    @Override
    public List<Film> findAll() {
        return filmEntityMapper.toDomain(filmRepository.findAll(SORT_BY_ASCENDING_ID));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> findAllByDirectorId(Long directorId, SortBy sortBy) {
        if (!directorPersistenceService.existsById(directorId)) {
            throw new NotFoundException(DirectorRepository.NOT_FOUND);
        }
        return filmEntityMapper.toDomain(filmRepository.findAllByDirectors_Id(directorId, Sort.by(sortBy.getCriteria())));
    }

    @Override
    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addOrDeleteLike(Long id, Long userId, Operation operation) {
        FilmEntity film = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FilmRepository.NOT_FOUND));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));
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
        eventPersistenceService.create(userId, EventType.LIKE, operation, id);
    }

    @Override
    public List<Film> findCommon(Long userId, Long friendId) {
        return filmEntityMapper.toDomain(filmRepository.findCommon(userId, friendId));
    }

    @Override
    public List<Film> findPopular(Integer count, Long genreId, Integer year) {
        Specification<FilmEntity> specification = filmSpecification.findPopular(genreId, year);
        Pageable pageable = PageRequest.of(0, count, SORT_BY_DESCENDING_LIKES_AMOUNT);

        return filmEntityMapper.toDomain(filmRepository.findAll(specification, pageable).getContent());
    }

    @Override
    public List<Film> search(String query, List<By> by) {
        Specification<FilmEntity> specification = filmSpecification.search(query, by);
        Sort sort = SORT_BY_DESCENDING_LIKES_AMOUNT.and(SORT_BY_ASCENDING_ID);

        return filmEntityMapper.toDomain(filmRepository.findAll(specification, sort));
    }
}
