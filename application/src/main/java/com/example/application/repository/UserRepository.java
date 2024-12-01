package com.example.application.repository;

import com.example.application.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с пользователями.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Получение пользователя со списком друзей.
     * @param id идентификатор пользователя
     */
    @Query(value = FIND_BY_ID_WITH_FRIENDS)
    Optional<User> findByIdWithFriends(@Param("id") Long id);

    /**
     * Получение списка всех общих друзей.
     * @param id идентификатор пользователя
     * @param otherUserId идентификатор пользователя
     */
    @Query(value = FIND_COMMON_FRIENDS, nativeQuery = true)
    List<User> findCommonFriends(@Param("id") Long id, @Param("otherUserId") Long otherUserId);

    /**
     * Получение списка всех идентификаторов релевантных пользователей.
     * @param id идентификатор пользователя
     */
    @Query(value = FIND_ALL_FOR_RECOMMENDATIONS, nativeQuery = true)
    List<Long> findAllForRecommendations(@Param("id") Long id);

    String FIND_BY_ID_WITH_FRIENDS = """
            SELECT user
            FROM User AS user
            LEFT JOIN FETCH user.friends
            WHERE user.id = :id
            """;

    String FIND_COMMON_FRIENDS = """
            SELECT *
            FROM users
            WHERE id in (
                SELECT receiving_user_id
                FROM friendship
                WHERE requesting_user_id = :id
                INTERSECT
                SELECT receiving_user_id
                FROM friendship
                WHERE requesting_user_id = :otherUserId
            );
            """;

    String FIND_ALL_FOR_RECOMMENDATIONS = """
            SELECT user_id
            FROM (
                SELECT user_id, max(count)
                FROM (
                    SELECT user_id, count(film_id) AS count
                    FROM film_likes
                    WHERE film_id in (
                        SELECT film_id
                        FROM film_likes
                        WHERE user_id = :id
                    ) AND user_id <> :id
                    GROUP BY user_id
                )
                GROUP BY user_id
            );
            """;
}
