package com.example.backend.dao;

import com.example.backend.entity.User;

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
