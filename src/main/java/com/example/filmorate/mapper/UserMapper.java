package com.example.filmorate.mapper;

import com.example.filmorate.entity.User;
import com.example.filmorate.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public User toUser(UserDto userDto) {
        String name = userDto.getName() == null || userDto.getName().isEmpty() ? userDto.getLogin() : userDto.getName();

        return new User(null, userDto.getEmail(), userDto.getLogin(), name, userDto.getBirthday(), Collections.emptyList());
    }

    public User toUser(UserDto userDto, User user) {
        String name = userDto.getName() == null ? user.getName() : userDto.getName();

        return new User(user.getId(), userDto.getEmail(), userDto.getLogin(), name, userDto.getBirthday(), user.getFriends());
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getLogin(), user.getName(), user.getBirthday(), user.getFriends());
    }

    public List<UserDto> toUserDto(List<User> users) {
        return users.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}
