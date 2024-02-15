package com.example.filmorate.dao.mapper;

import com.example.filmorate.dao.UserDao;
import com.example.filmorate.entity.User;
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
        List<Integer> friends = userDao.findAllFriends(rs.getInt("id"), true);

        return new User(rs.getInt("id"), rs.getString("email"), rs.getString("login"), rs.getString("name"),  rs.getDate("birthday").toLocalDate(), friends);
    }
}
