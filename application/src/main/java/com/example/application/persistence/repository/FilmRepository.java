package com.example.application.persistence.repository;

import com.example.application.persistence.model.FilmEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для взаимодействия с фильмами.
 */
@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long>, JpaSpecificationExecutor<FilmEntity> {

    String NOT_FOUND = "Фильм с указанным идентификатором не найден";

    /**
     * Получение списка всех фильмов указанного режиссёра.
     *
     * @param directorId идентификатор режиссёра
     */
    List<FilmEntity> findAllByDirectors_Id(Long directorId, Sort sort);

    /**
     * Получение списка всех общих фильмов.
     *
     * @param userId   идентификатор пользователя
     * @param friendId идентификатор пользователя
     */
    @Query(nativeQuery = true)
    List<FilmEntity> findCommon(Long userId, Long friendId);

    /**
     * Обновление количества положительных оценок фильма.
     *
     * @param userId идентификатор пользователя
     */
    @Modifying
    @Query(nativeQuery = true)
    void decreaseLikesAmount(Long userId);

    /**
     * Получение списка всех фильмов, рекомендованных к просмотру
     *
     * @param userId идентификатор пользователя
     * @param ids    список идентификаторов релевантных пользователей
     */
    @Query(nativeQuery = true)
    List<FilmEntity> findRecommendations(Long userId, Iterable<Long> ids);
}
