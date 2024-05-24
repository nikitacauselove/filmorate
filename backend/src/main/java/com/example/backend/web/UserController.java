package com.example.backend.web;

import com.example.api.UserApi;
import com.example.api.dto.FilmDto;
import com.example.api.dto.UserDto;
import com.example.backend.entity.User;
import com.example.backend.mapper.FilmMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final FilmMapper filmMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    public UserDto create(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);

        return userMapper.mapToUserDto(userService.create(user));
    }

    public UserDto update(UserDto userDto) {
        User user = userMapper.mapToUser(userDto, userService.findById(userDto.id()));

        return userMapper.mapToUserDto(userService.update(user));
    }

    public UserDto findById(Integer id) {
        return userMapper.mapToUserDto(userService.findById(id));
    }

    public List<UserDto> findAll() {
        return userMapper.mapToUserDto(userService.findAll());
    }

    public void deleteById(Integer userId) {
        userService.deleteById(userId);
    }

    public void addFriend(Integer id, Integer friendId) {
        userService.addFriend(id, friendId);
    }

    public void deleteFriend(Integer id, Integer friendId) {
        userService.deleteFriend(id, friendId);
    }

    public List<UserDto> findAllFriends(Integer id) {
        return userMapper.mapToUserDto(userService.findAllFriends(id));
    }

    public List<UserDto> findCommonFriends(Integer id, Integer otherId) {
        return userMapper.mapToUserDto(userService.findCommonFriends(id, otherId));
    }

    public List<FilmDto> findRecommendations(Integer id) {
        return filmMapper.mapToFilmDto(userService.findRecommendations(id));
    }
}
