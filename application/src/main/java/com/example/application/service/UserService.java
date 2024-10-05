package com.example.application.service;

import com.example.api.dto.UserDto;
import com.example.application.repository.entity.Film;
import com.example.application.repository.entity.User;

import java.util.List;

public interface UserService {

    User create(UserDto userDto);

    User update(UserDto userDto);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    void addFriend(Long id, Long friendId);

    void deleteFriend(Long id, Long friendId);

    List<User> findAllFriends(Long id);

    List<User> findCommonFriends(Long id, Long otherUserId);

    List<Film> findRecommendations(Long id);
}
