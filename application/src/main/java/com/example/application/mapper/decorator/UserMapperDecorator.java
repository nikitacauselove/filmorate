package com.example.application.mapper.decorator;

import com.example.api.dto.UserDto;
import com.example.application.mapper.UserMapper;
import com.example.application.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserMapperDecorator implements UserMapper {

    private UserMapper delegate;

    @Autowired
    public void setDelegate(UserMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public User toUser(UserDto userDto) {
        User user = delegate.toUser(userDto);

        user.setName(userDto.name() == null || userDto.name().isEmpty() ? userDto.login() : userDto.name());
        user.setFriends(java.util.Collections.emptySet());
        return user;
    }
}
