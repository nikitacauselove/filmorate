package com.example.backend.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import com.example.api.dto.UserDto;
import com.example.backend.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public abstract class UserMapper {

    public User mapToUser(UserDto userDto) {
        String name = userDto.name() == null || userDto.name().isEmpty() ? userDto.login() : userDto.name();

        return new User(null, userDto.email(), userDto.login(), name, userDto.birthday(), Collections.emptySet());
    }

    public abstract User updateUser(UserDto userDto, @MappingTarget User user);

    public abstract UserDto mapToUserDto(User user);

    public abstract List<UserDto> mapToUserDto(List<User> userList);
}
