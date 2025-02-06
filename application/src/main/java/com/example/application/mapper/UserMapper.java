package com.example.application.mapper;

import com.example.api.model.UserDto;
import com.example.application.entity.User;
import com.example.application.mapper.decorator.UserMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;

@DecoratedWith(UserMapperDecorator.class)
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "friends", source = "userSet")
    User toEntity(UserDto userDto, Set<User> userSet);

    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "friends", ignore = true)
    User updateEntity(UserDto userDto, @MappingTarget User user);

    UserDto toDto(User user);

    List<UserDto> toDto(List<User> userList);
}
