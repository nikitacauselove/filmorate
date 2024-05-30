package com.example.backend.service;

import com.example.backend.entity.Film;
import com.example.backend.entity.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    void addFriend(Long id, Long friendId);

    void deleteFriend(Long id, Long friendId);

    List<User> findAllFriends(Long id);

    List<User> findCommonFriends(Long id, Long otherUserId);

    List<Film> findRecommendations(Long id);
}
