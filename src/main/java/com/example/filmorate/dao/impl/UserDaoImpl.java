package com.example.filmorate.dao.impl;

import com.example.filmorate.dao.UserDao;
import com.example.filmorate.dao.mapper.UserMapper;
import com.example.filmorate.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserDaoImpl extends DaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_FRIENDS_SQL = "select receiving_user_id from friendship where requesting_user_id = ?";

    @Override
    public void create(User user) {
        String sql = "insert into users (id, email, login, name, birthday) values (?, ?, ?, ?, ?)";
        user.setId(getNextId());

        jdbcTemplate.update(sql, user.getId(), user.getEmail(), user.getLogin(), user.getName(), user.getBirthday());
    }

    @Override
    public void update(User user) {
        String sql = "update users set email = ?, login = ?, name = ?, birthday = ? where id = ?";

        jdbcTemplate.update(sql, user.getEmail(), user.getLogin(), user.getName(), user.getBirthday(), user.getId());
    }

    @Override
    public User findById(int id) {
        try {
            return jdbcTemplate.queryForObject("select * from users where id = ?", new UserMapper(this), id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден.");
        }
    }

    @Override
    public boolean existsById(int id) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject("select exists (select * from users where id = ?)", Boolean.class, id));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", new UserMapper(this));
    }

    @Override
    public void deleteById(int userId) {
        jdbcTemplate.update("delete from users where id = ?", userId);
    }

    @Override
    public void addFriend(int id, int friendId) {
        jdbcTemplate.update("insert into friendship (requesting_user_id, receiving_user_id) values (?, ?)", id, friendId);
    }

    @Override
    public void deleteFriend(int id, int friendId) {
        jdbcTemplate.update("delete from friendship where requesting_user_id = ? and receiving_user_id = ?", id, friendId);
    }

    @Override
    public List<User> findAllFriends(int id) {
        List<Integer> listOfId = findAllFriends(id, true);

        return jdbcTemplate.query(String.format("select * from users where id in (%s)", inSql(listOfId)), new UserMapper(this));
    }

    @Override
    public List<Integer> findAllFriends(int id, boolean idOnly) {
        return jdbcTemplate.queryForList(FIND_ALL_FRIENDS_SQL, Integer.class, id);
    }

    @Override
    public List<User> findCommonFriends(int id, int otherId) {
        List<Integer> listOfId = jdbcTemplate.queryForList(String.format("%s intersect %s", FIND_ALL_FRIENDS_SQL, FIND_ALL_FRIENDS_SQL), Integer.class, id, otherId);

        return jdbcTemplate.query(String.format("select * from users where id in (%s)", inSql(listOfId)), new UserMapper(this));
    }
}
