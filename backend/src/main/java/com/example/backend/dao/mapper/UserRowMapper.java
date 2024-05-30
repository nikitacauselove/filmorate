package com.example.backend.dao.mapper;

import com.example.backend.dao.UserDao;
import com.example.backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class UserRowMapper implements RowMapper<User> {

    private final UserDao userDao;

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Long> friendList = userDao.findAllFriends(rs.getLong("id"), true);

        return new User(rs.getLong("id"), rs.getString("email"), rs.getString("login"), rs.getString("name"),  rs.getDate("birthday").toLocalDate(), friendList);
    }
}
