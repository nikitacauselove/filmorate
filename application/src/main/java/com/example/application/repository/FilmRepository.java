package com.example.application.repository;

import com.example.application.repository.entity.Film;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для взаимодействия с фильмами.
 */
@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {

    /**
     * Получение списка всех фильмов определенного режиссёра.
     * @param directorId идентификатор режиссёра
     */
    List<Film> findAllByDirectors_Id(Long directorId, Sort sort);

    /**
     * Получение списка всех фильмов, рекомендованных для просмотра.
     * @param ids список всех идентификаторов релевантных пользователей
     * @param userId идентификатор пользователя
     */
    @Query(value = FIND_RECOMMENDATIONS, nativeQuery = true)
    List<Film> findRecommendations(@Param("ids") Iterable<Long> ids, @Param("userId") Long userId);

    /**
     * Получение списка всех общих фильмов.
     * @param userId идентификатор пользователя
     * @param friendId идентификатор пользователя
     */
    @Query(value = FIND_COMMON, nativeQuery = true)
    List<Film> findCommon(Long userId, Long friendId);

    /**
     * Обновление количества положительных оценок фильма.
     * @param userId идентификатор пользователя
     */
    @Modifying
    @Query(value = DECREASE_LIKES_AMOUNT, nativeQuery = true)
    void decreaseLikesAmount(@Param("userId") Long userId);

    String FIND_RECOMMENDATIONS = """
            SELECT *
            FROM films
            WHERE id in (
                SELECT film_id
                FROM film_likes
                WHERE user_id in :ids
                EXCEPT
                SELECT film_id
                FROM film_likes
                WHERE user_id = :userId
            );
            """;

    String FIND_COMMON = """
            SELECT *
            FROM films
            WHERE id in (
                SELECT film_id
                FROM film_likes
                WHERE user_id = :userId
                INTERSECT
                SELECT film_id
                FROM film_likes
                WHERE user_id = :friendId
            );
            """;

    String DECREASE_LIKES_AMOUNT = """
            UPDATE films SET likes_amount = likes_amount - 1
            WHERE id in (
                SELECT film_id
                FROM film_likes
                WHERE user_id = :userId
            );
            """;
}
