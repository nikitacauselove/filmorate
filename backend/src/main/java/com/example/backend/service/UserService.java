package com.example.backend.service;

import com.example.backend.entity.Film;
import com.example.backend.entity.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user);

    User findById(int id);

    List<User> findAll();

    void deleteById(int userId);

    void addFriend(int id, int friendId);

    void deleteFriend(int id, int friendId);

    List<User> findAllFriends(int id);

    List<User> findCommonFriends(int id, int otherId);

    List<Film> findRecommendations(int id);
}
