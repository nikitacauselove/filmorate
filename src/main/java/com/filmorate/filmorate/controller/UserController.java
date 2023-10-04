package com.filmorate.filmorate.controller;

import com.filmorate.filmorate.model.User;
import com.filmorate.filmorate.model.dto.FilmDto;
import com.filmorate.filmorate.model.dto.UserDto;
import com.filmorate.filmorate.model.mapper.FilmMapper;
import com.filmorate.filmorate.model.mapper.UserMapper;
import com.filmorate.filmorate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
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
    public UserDto addFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.addFriend(id, friendId);

        return UserMapper.toUserDto(userService.findById(id));
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public UserDto deleteFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.deleteFriend(id, friendId);

        return UserMapper.toUserDto(userService.findById(id));
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
