package com.example.filmorate.mapper;

import com.example.filmorate.entity.User;
import com.example.filmorate.dto.UserDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toUser(UserDto userDto) {
        String name = userDto.getName() == null || userDto.getName().isEmpty() ? userDto.getLogin() : userDto.getName();

        return new User(null, userDto.getEmail(), userDto.getLogin(), name, userDto.getBirthday(), Collections.emptyList());
    }

    public static User toUser(UserDto userDto, User user) {
        String name = userDto.getName() == null ? user.getName() : userDto.getName();

        return new User(user.getId(), userDto.getEmail(), userDto.getLogin(), name, userDto.getBirthday(), user.getFriends());
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getLogin(), user.getName(), user.getBirthday(), user.getFriends());
    }

    public static List<UserDto> toUserDto(List<User> users) {
        return users.stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
