package com.example.application.service;

import com.example.application.domain.User;

import java.util.List;

/**
 * Сервис для взаимодействия с пользователями.
 */
public interface UserService {

    /**
     * Добавление нового пользователя.
     *
     * @param user информация о пользователе
     */
    User create(User user);

    /**
     * Обновление информации о пользователе.
     *
     * @param user информация о пользователе
     */
    User update(User user);

    /**
     * Получение информации о пользователе.
     *
     * @param id идентификатор пользователя
     */
    User findById(Long id);

    /**
     * Получение списка всех пользователей.
     */
    List<User> findAll();

    /**
     * Удаление пользователя.
     *
     * @param id идентификатор пользователя
     */
    void deleteById(Long id);

    /**
     * Добавление пользователя в список друзей.
     *
     * @param id       идентификатор пользователя
     * @param friendId идентификатор пользователя
     */
    void addFriend(Long id, Long friendId);

    /**
     * Удаление пользователя из списка друзей.
     *
     * @param id       идентификатор пользователя
     * @param friendId идентификатор пользователя
     */
    void deleteFriend(Long id, Long friendId);

    /**
     * Получение списка всех друзей пользователя.
     *
     * @param id идентификатор пользователя
     */
    List<User> findFriends(Long id);

    /**
     * Получение списка всех общих друзей.
     *
     * @param id          идентификатор пользователя
     * @param otherUserId идентификатор пользователя
     */
    List<User> findCommonFriends(Long id, Long otherUserId);
}
