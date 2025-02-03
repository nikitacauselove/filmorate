package com.example.application.persistence.mapper;

import com.example.application.domain.User;
import com.example.application.persistence.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "friends", ignore = true)
    UserEntity toEntity(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "friends", ignore = true)
    UserEntity updateEntity(User user, @MappingTarget UserEntity userEntity);

    User toDomain(UserEntity userEntity);

    List<User> toDomain(List<UserEntity> userEntityList);
}
