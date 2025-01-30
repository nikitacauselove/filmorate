package com.example.application.controller;

import com.example.api.UserApi;
import com.example.api.model.FilmDto;
import com.example.api.model.UserDto;
import com.example.api.model.type.Operation;
import com.example.application.controller.mapper.FilmDtoMapper;
import com.example.application.controller.mapper.UserDtoMapper;
import com.example.application.domain.User;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final FilmDtoMapper filmDtoMapper;
    private final UserDtoMapper userDtoMapper;
    private final UserService userService;

    @Override
    public UserDto create(UserDto userDto) {
        User user = userDtoMapper.toDomain(userDto);

        return userDtoMapper.toDto(userService.create(user));
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userDtoMapper.toDomain(userDto);

        return userDtoMapper.toDto(userService.update(user));
    }

    @Override
    public UserDto findById(Long id) {
        return userDtoMapper.toDto(userService.findById(id));
    }

    @Override
    public List<UserDto> findAll() {
        return userDtoMapper.toDto(userService.findAll());
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
    public List<UserDto> findFriends(Long id) {
        return userDtoMapper.toDto(userService.findFriends(id));
    }

    @Override
    public List<UserDto> findCommonFriends(Long id, Long otherUserId) {
        return userDtoMapper.toDto(userService.findCommonFriends(id, otherUserId));
    }

    @Override
    public List<FilmDto> findRecommendations(Long id) {
        return filmDtoMapper.toDto(userService.findRecommendations(id));
    }
}
