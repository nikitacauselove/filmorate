package com.example.backend.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import com.example.api.dto.UserDto;
import com.example.backend.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", expression = "java(userDto.name() == null || userDto.name().isEmpty() ? userDto.login() : userDto.name())")
    @Mapping(target = "friends", expression = "java(java.util.Collections.emptySet())")
    User mapToUser(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "friends", ignore = true)
    User updateUser(UserDto userDto, @MappingTarget User user);

    UserDto mapToUserDto(User user);

    List<UserDto> mapToUserDto(List<User> userList);
}
