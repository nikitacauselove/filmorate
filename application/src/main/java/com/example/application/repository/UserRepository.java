package com.example.application.repository;

import com.example.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с пользователями.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    String NOT_FOUND = "Пользователь с указанным идентификатором не найден";

    /**
     * Получение информации о пользователе.
     *
     * @param id идентификатор пользователя
     */
    @Query
    Optional<User> findByIdWithFriends(Long id);

    /**
     * Получение списка всех общих друзей.
     *
     * @param id          идентификатор пользователя
     * @param otherUserId идентификатор пользователя
     */
    @Query(nativeQuery = true)
    List<User> findCommonFriends(Long id, Long otherUserId);

    /**
     * Получение списка всех идентификаторов релевантных пользователей.
     *
     * @param id идентификатор пользователя
     */
    @Query(nativeQuery = true)
    List<Long> findRelevant(Long id);
}
