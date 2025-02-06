package com.example.application.controller;

import com.example.api.UserApi;
import com.example.api.model.FilmDto;
import com.example.api.model.UserDto;
import com.example.application.entity.User;
import com.example.application.mapper.FilmMapper;
import com.example.application.mapper.UserMapper;
import com.example.application.service.FilmService;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final FilmMapper filmMapper;
    private final FilmService filmService;
    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto, Collections.emptySet());

        return userMapper.toDto(userService.create(user));
    }

    @Override
    public UserDto update(UserDto userDto) {
        return userMapper.toDto(userService.update(userDto));
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userService.findById(id));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toDto(userService.findAll());
    }

    @Override
    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    @Override
    public void addFriend(Long id, Long friendId) {
        userService.addFriend(id, friendId);
    }

    @Override
    public void deleteFriend(Long id, Long friendId) {
        userService.deleteFriend(id, friendId);
    }

    @Override
    public List<UserDto> findFriends(Long id) {
        return userMapper.toDto(userService.findFriends(id));
    }

    @Override
    public List<UserDto> findCommonFriends(Long id, Long otherUserId) {
        return userMapper.toDto(userService.findCommonFriends(id, otherUserId));
    }

    @Override
    public List<FilmDto> findRecommendations(Long id) {
        return filmMapper.toDto(filmService.findRecommendations(id));
    }
}
