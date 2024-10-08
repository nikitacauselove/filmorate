package com.example.application.repository;

import com.example.application.repository.entity.Film;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {

    List<Film> findAllByDirectors_Id(Long directorId, Sort sort);

    @Query(value = FIND_RECOMMENDATIONS, nativeQuery = true)
    List<Film> findRecommendations(@Param("listOfUserId") Iterable<Long> listOfUserId, @Param("userId") Long userId);

    @Query(value = FIND_COMMON, nativeQuery = true)
    List<Film> findCommon(Long userId, Long friendId);

    @Modifying
    @Query(value = UPDATE_LIKES_AMOUNT, nativeQuery = true)
    void updateLikesAmount(@Param("userId") Long userId);

    String FIND_RECOMMENDATIONS = """
            SELECT *
            FROM films
            WHERE id in (
                SELECT film_id
                FROM film_likes
                WHERE user_id in :listOfUserId
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

    String UPDATE_LIKES_AMOUNT = """
            UPDATE films SET likes_amount = likes_amount - 1
            WHERE id in (
                SELECT film_id
                FROM film_likes
                WHERE user_id = :userId
            );
            """;
}
