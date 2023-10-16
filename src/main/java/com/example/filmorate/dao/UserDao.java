package com.example.filmorate.dao;

import com.example.filmorate.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    void update(User user);

    User findById(int id);

    boolean existsById(int id);

    List<User> findAll();

    void deleteById(int userId);

    void addFriend(int id, int friendId);

    void deleteFriend(int id, int friendId);

    List<User> findAllFriends(int id);

    List<Integer> findAllFriends(int id, boolean idOnly);

    List<User> findCommonFriends(int id, int otherId);
}
