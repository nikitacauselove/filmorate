package com.example.backend.dao;

import com.example.backend.entity.User;

import java.util.List;

public interface UserDao {

    void create(User user);

    void update(User user);

    User findById(Long id);

    boolean existsById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    void addFriend(Long id, Long friendId);

    void deleteFriend(Long id, Long friendId);

    List<User> findAllFriends(Long id);

    List<Long> findAllFriends(Long id, Boolean idOnly);

    List<User> findCommonFriends(Long id, Long otherUserId);
}
