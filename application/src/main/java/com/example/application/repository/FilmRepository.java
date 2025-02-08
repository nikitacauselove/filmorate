package com.example.application.repository;

import com.example.application.entity.Film;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с фильмами.
 */
public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {

    String NOT_FOUND = "Фильм с указанным идентификатором не найден";

    /**
     * Получение информации о фильме.
     *
     * @param id идентификатор фильма
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {Film.Fields.genres, Film.Fields.directors})
    @Override
    Optional<Film> findById(Long id);

    /**
     * Получение списка всех фильмов.
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {Film.Fields.genres, Film.Fields.directors})
    @Override
    List<Film> findAll(Sort sort);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {Film.Fields.genres, Film.Fields.directors})
    @Override
    List<Film> findAllById(Iterable<Long> ids);

    /**
     * Получение списка всех фильмов указанного режиссёра.
     *
     * @param directorId идентификатор режиссёра
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {Film.Fields.genres, Film.Fields.directors})
    List<Film> findAllByDirectors_Id(Long directorId, Sort sort);

    @Query(nativeQuery = true)
    List<Long> findRecommendations(Long userId, Iterable<Long> ids);

    /**
     * Получение списка всех общих фильмов.
     *
     * @param userId   идентификатор пользователя
     * @param friendId идентификатор пользователя
     */
    @Query(nativeQuery = true)
    List<Long> findCommon(Long userId, Long friendId);

    /**
     * Обновление количества положительных оценок фильмов.
     *
     * @param userId идентификатор пользователя
     */
    @Modifying
    @Query(nativeQuery = true)
    void decreaseLikesAmount(Long userId);
}
