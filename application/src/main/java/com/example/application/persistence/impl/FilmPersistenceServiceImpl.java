package com.example.application.persistence.impl;

import com.example.application.domain.By;
import com.example.application.domain.Film;
import com.example.application.domain.SortBy;
import com.example.application.exception.NotFoundException;
import com.example.application.persistence.FilmPersistenceService;
import com.example.application.persistence.mapper.DirectorEntityMapper;
import com.example.application.persistence.mapper.FilmEntityMapper;
import com.example.application.persistence.model.DirectorEntity;
import com.example.application.persistence.model.FilmEntity;
import com.example.application.persistence.model.UserEntity;
import com.example.application.persistence.repository.DirectorRepository;
import com.example.application.persistence.repository.FilmRepository;
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
    private final FilmRepository filmRepository;
    private final FilmSpecification filmSpecification;
    private final UserRepository userRepository;
    private final FilmEntityMapper filmEntityMapper;
    private final DirectorRepository directorRepository;

    @Override
    @Transactional
    public Film create(Film film) {
        List<DirectorEntity> directors = directorRepository.findAllById(directorEntityMapper.toIds(film.directors()));
        FilmEntity filmEntity = filmEntityMapper.toEntity(film, Set.copyOf(directors), Collections.emptySet());

        return filmEntityMapper.toDomain(filmRepository.save(filmEntity));
    }

    @Override
    @Transactional
    public Film update(Film film) {
        List<DirectorEntity> directorEntityList = directorRepository.findAllById(directorEntityMapper.toIds(film.directors()));
        FilmEntity filmEntity = filmRepository.findById(film.id())
                .orElseThrow(() -> new NotFoundException(FilmRepository.NOT_FOUND));

        return filmEntityMapper.toDomain(filmEntityMapper.updateEntity(film, Set.copyOf(directorEntityList), filmEntity));
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
        if (!directorRepository.existsById(directorId)) {
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
    public void addLike(Long id, Long userId) {
        FilmEntity filmEntity = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FilmRepository.NOT_FOUND));
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));

        if (!filmEntity.getLikingUsers().contains(userEntity)) {
            filmEntity.getLikingUsers().add(userEntity);
            filmEntity.setLikesAmount(filmEntity.getLikesAmount() + 1);
        }
    }

    @Override
    @Transactional
    public void deleteLike(Long id, Long userId) {
        FilmEntity filmEntity = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(FilmRepository.NOT_FOUND));
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));

        if (filmEntity.getLikingUsers().contains(userEntity)) {
            filmEntity.getLikingUsers().remove(userEntity);
            filmEntity.setLikesAmount(filmEntity.getLikesAmount() - 1);
        }
    }

    @Override
    public void decreaseLikesAmount(Long userId) {
        filmRepository.decreaseLikesAmount(userId);
    }

    @Override
    public List<Film> findCommon(Long userId, Long friendId) {
        return filmEntityMapper.toDomain(filmRepository.findCommon(userId, friendId));
    }

    @Override
    public List<Film> findPopular(Integer count, Long genreId, Integer year) {
        Specification<FilmEntity> specification = filmSpecification.byGenres(genreId)
                .and(filmSpecification.byReleaseDate(year));
        Pageable pageable = PageRequest.of(0, count, SORT_BY_DESCENDING_LIKES_AMOUNT);

        return filmEntityMapper.toDomain(filmRepository.findAll(specification, pageable).getContent());
    }

    @Override
    @Transactional
    public List<Film> findRecommendations(Long id) {
        List<Long> ids = userRepository.findRelevantUserIds(id);

        return filmEntityMapper.toDomain(filmRepository.findRecommendations(id, ids));
    }

    @Override
    public List<Film> search(String query, List<By> by) {
        Specification<FilmEntity> specification = filmSpecification.byDirectors(query, by)
                .or(filmSpecification.byName(query, by));
        Sort sort = SORT_BY_DESCENDING_LIKES_AMOUNT.and(SORT_BY_ASCENDING_ID);

        return filmEntityMapper.toDomain(filmRepository.findAll(specification, sort));
    }
}
