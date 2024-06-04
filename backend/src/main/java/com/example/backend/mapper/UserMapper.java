package com.example.backend.mapper;

import com.example.api.dto.UserDto;
import com.example.backend.entity.User;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public User mapToUser(UserDto userDto) {
        String name = userDto.name() == null || userDto.name().isEmpty() ? userDto.login() : userDto.name();

        return new User(null, userDto.email(), userDto.login(), name, userDto.birthday(), Collections.emptySet());
    }

    public User mapToUser(UserDto userDto, User user) {
        String name = userDto.name() == null ? user.getName() : userDto.name();

        return new User(user.getId(), userDto.email(), userDto.login(), name, userDto.birthday(), user.getFriends());
    }

    public abstract UserDto mapToUserDto(User user);

    public abstract List<UserDto> mapToUserDto(List<User> userList);
}
