package com.example.application.controller;

import com.example.api.UserApi;
import com.example.api.dto.FilmDto;
import com.example.api.dto.UserDto;
import com.example.api.dto.enums.Operation;
import com.example.application.mapper.FilmMapper;
import com.example.application.mapper.UserMapper;
import com.example.application.repository.entity.User;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final FilmMapper filmMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public UserDto create(UserDto userDto) {
        User user = userMapper.toUser(userDto);

        return userMapper.toUserDto(userService.create(user));
    }

    @Override
    public UserDto update(UserDto userDto) {
        return userMapper.toUserDto(userService.update(userDto));
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toUserDto(userService.findById(id));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toUserDto(userService.findAll());
    }

    @Override
    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    @Override
    public void addFriend(Long id, Long friendId) {
        userService.addOrDeleteFriend(id, friendId, Operation.ADD);
    }

    @Override
    public void deleteFriend(Long id, Long friendId) {
        userService.addOrDeleteFriend(id, friendId, Operation.REMOVE);
    }

    @Override
    public List<UserDto> findAllFriends(Long id) {
        return userMapper.toUserDto(userService.findAllFriends(id));
    }

    @Override
    public List<UserDto> findCommonFriends(Long id, Long otherUserId) {
        return userMapper.toUserDto(userService.findCommonFriends(id, otherUserId));
    }

    @Override
    public List<FilmDto> findRecommendations(Long id) {
        return filmMapper.toFilmDto(userService.findRecommendations(id));
    }
}
