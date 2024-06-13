package com.example.backend.web;

import com.example.api.UserApi;
import com.example.api.dto.FilmDto;
import com.example.api.dto.UserDto;
import com.example.backend.repository.entity.User;
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
        User user = userMapper.toUser(userDto);

        return userMapper.toUserDto(userService.create(user));
    }

    public UserDto update(UserDto userDto) {
        User user = userMapper.updateUser(userDto, userService.findById(userDto.id()));

        return userMapper.toUserDto(userService.update(user));
    }

    public UserDto findById(Long id) {
        return userMapper.toUserDto(userService.findById(id));
    }

    public List<UserDto> findAll() {
        return userMapper.toUserDto(userService.findAll());
    }

    public void deleteById(Long id) {
        userService.deleteById(id);
    }

    public void addFriend(Long id, Long friendId) {
        userService.addFriend(id, friendId);
    }

    public void deleteFriend(Long id, Long friendId) {
        userService.deleteFriend(id, friendId);
    }

    public List<UserDto> findAllFriends(Long id) {
        return userMapper.toUserDto(userService.findAllFriends(id));
    }

    public List<UserDto> findCommonFriends(Long id, Long otherUserId) {
        return userMapper.toUserDto(userService.findCommonFriends(id, otherUserId));
    }

    public List<FilmDto> findRecommendations(Long id) {
        return filmMapper.toFilmDto(userService.findRecommendations(id));
    }
}
