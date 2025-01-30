package com.example.application.controller.mapper.decorator;

import com.example.api.model.UserDto;
import com.example.application.controller.mapper.UserDtoMapper;
import com.example.application.domain.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter(onMethod_ = @Autowired)
public abstract class UserDtoMapperDecorator implements UserDtoMapper {

    private UserDtoMapper delegate;

    @Override
    public User toDomain(UserDto userDto) {
        User user = delegate.toDomain(userDto);
        User.UserBuilder userBuilder = user.toBuilder();

        userBuilder.name(userDto.name() == null || userDto.name().isEmpty() ? userDto.login() : userDto.name());
        return userBuilder.build();
    }
}
