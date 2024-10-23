package com.example.application.mapper;

import com.example.api.dto.UserDto;
import com.example.application.mapper.decorator.UserMapperDecorator;
import com.example.application.repository.entity.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@DecoratedWith(UserMapperDecorator.class)
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "friends", ignore = true)
    User toUser(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "friends", ignore = true)
    User updateUser(UserDto userDto, @MappingTarget User user);

    UserDto toUserDto(User user);

    List<UserDto> toUserDto(List<User> userList);
}
