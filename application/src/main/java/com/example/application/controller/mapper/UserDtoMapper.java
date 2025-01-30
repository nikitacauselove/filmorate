package com.example.application.controller.mapper;

import com.example.api.model.UserDto;
import com.example.application.controller.mapper.decorator.UserDtoMapperDecorator;
import com.example.application.domain.User;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@DecoratedWith(UserDtoMapperDecorator.class)
@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(target = "name", ignore = true)
    User toDomain(UserDto userDto);

    UserDto toDto(User user);

    List<UserDto> toDto(List<User> userList);
}
