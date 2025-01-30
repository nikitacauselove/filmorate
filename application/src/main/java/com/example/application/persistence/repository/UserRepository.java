package com.example.application.persistence.repository;

import com.example.application.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с пользователями.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    String NOT_FOUND = "Пользователь с указанным идентификатором не найден";

    /**
     * Получение информации о пользователе.
     *
     * @param id идентификатор пользователя
     */
    @Query
    Optional<UserEntity> findByIdWithFriends(Long id);

    /**
     * Получение списка всех общих друзей.
     *
     * @param id идентификатор пользователя
     * @param otherUserId идентификатор пользователя
     */
    @Query(nativeQuery = true)
    List<UserEntity> findCommonFriends(Long id, Long otherUserId);

    /**
     * Получение списка всех идентификаторов релевантных пользователей.
     *
     * @param id идентификатор пользователя
     */
    @Query(nativeQuery = true)
    List<Long> findRelevantUserIds(Long id);
}
