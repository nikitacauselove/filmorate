package com.example.application.mapper.decorator;

import com.example.api.model.UserDto;
import com.example.application.entity.User;
import com.example.application.mapper.UserMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Setter(onMethod_ = @Autowired)
public abstract class UserMapperDecorator implements UserMapper {

    private UserMapper delegate;

    @Override
    public User toEntity(UserDto userDto, Set<User> userSet) {
        User user = delegate.toEntity(userDto, userSet);

        user.setName(userDto.name() == null || userDto.name().isEmpty() ? userDto.login() : userDto.name());
        return user;
    }
}
