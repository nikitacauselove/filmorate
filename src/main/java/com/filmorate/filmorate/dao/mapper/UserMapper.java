package com.filmorate.filmorate.dao.mapper;

import com.filmorate.filmorate.dao.UserDao;
import com.filmorate.filmorate.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class UserMapper implements RowMapper<User> {
    private final UserDao userDao;

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Integer> friends = userDao.findAllFriends(rs.getInt("id"), true);

        return new User(rs.getInt("id"), rs.getString("email"), rs.getString("login"), rs.getString("name"),  rs.getDate("birthday").toLocalDate(), friends);
    }
}
