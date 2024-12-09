package com.example.application.mapper.decorator;

import com.example.api.dto.UserDto;
import com.example.application.mapper.UserMapper;
import com.example.application.repository.entity.User;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Setter(onMethod_ = @Autowired)
public abstract class UserMapperDecorator implements UserMapper {

    private UserMapper delegate;

    @Override
    public User toUser(UserDto userDto) {
        User user = delegate.toUser(userDto);

        user.setName(userDto.name() == null || userDto.name().isEmpty() ? userDto.login() : userDto.name());
        user.setFriends(Collections.emptySet());
        return user;
    }
}
