package com.example.filmorate.controller;

import com.example.filmorate.service.impl.UserService;
import com.example.filmorate.entity.User;
import com.example.filmorate.dto.FilmDto;
import com.example.filmorate.dto.UserDto;
import com.example.filmorate.mapper.FilmMapper;
import com.example.filmorate.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody @Valid UserDto userDto) {
        User user = UserMapper.toUser(userDto);

        return UserMapper.toUserDto(userService.create(user));
    }

    @PutMapping
    public UserDto update(@RequestBody @Valid UserDto userDto) {
        User user = UserMapper.toUser(userDto, userService.findById(userDto.getId()));

        return UserMapper.toUserDto(userService.update(user));
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable int id) {
        return UserMapper.toUserDto(userService.findById(id));
    }

    @GetMapping
    public List<UserDto> findAll() {
        return UserMapper.toUserDto(userService.findAll());
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable int userId) {
        userService.deleteById(userId);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.deleteFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<UserDto> findAllFriends(@PathVariable int id) {
        return UserMapper.toUserDto(userService.findAllFriends(id));
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<UserDto> findCommonFriends(@PathVariable int id, @PathVariable int otherId) {
        return UserMapper.toUserDto(userService.findCommonFriends(id, otherId));
    }

    @GetMapping("/{id}/recommendations")
    public List<FilmDto> findRecommendations(@PathVariable int id) {
        return FilmMapper.toFilmDto(userService.findRecommendations(id));
    }
}
