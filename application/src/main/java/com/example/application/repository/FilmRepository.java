package com.example.application.repository;

import com.example.application.repository.entity.Film;
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
public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {

    /**
     * Получение списка всех фильмов указанного режиссёра.
     * @param directorId идентификатор режиссёра
     */
    List<Film> findAllByDirectors_Id(Long directorId, Sort sort);

    /**
     * Получение списка всех фильмов, рекомендованных для просмотра.
     * @param ids список всех идентификаторов релевантных пользователей
     * @param userId идентификатор пользователя
     */
    @Query(nativeQuery = true)
    List<Film> findRecommendations(Iterable<Long> ids, Long userId);

    /**
     * Получение списка всех общих фильмов.
     * @param userId идентификатор пользователя
     * @param friendId идентификатор пользователя
     */
    @Query(nativeQuery = true)
    List<Film> findCommon(Long userId, Long friendId);

    /**
     * Обновление количества положительных оценок фильма.
     * @param userId идентификатор пользователя
     */
    @Modifying
    @Query(nativeQuery = true)
    void decreaseLikesAmount(Long userId);
}
