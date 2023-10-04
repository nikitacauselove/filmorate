package com.filmorate.filmorate.storage;

import com.filmorate.filmorate.dao.UserDao;
import com.filmorate.filmorate.model.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserDaoTest {
    private final UserDao userDao;

    private static final User FIRST_USER = new User(1, "mail@yandex.ru", "doloreUpdate", "est adipisicing", LocalDate.parse("1976-09-20"), List.of(3));
    private static final User SECOND_USER = new User(2, "friend@mail.ru", "friend", "friend adipisicing", LocalDate.parse("1976-08-20"), List.of(3));
    private static final User THIRD_USER = new User(3, "friend@common.ru", "common", "common", LocalDate.parse("2000-08-20"), Collections.emptyList());

    @Test
    public void findAll() {
        Assertions.assertEquals(userDao.findAll(), List.of(FIRST_USER, SECOND_USER, THIRD_USER));
    }

    @Test
    public void findUserById() {
        Assertions.assertEquals(userDao.findById(FIRST_USER.getId()), FIRST_USER);
    }

    @Test
    public void findAllFriends() {
        Assertions.assertEquals(userDao.findAllFriends(FIRST_USER.getId()), List.of(THIRD_USER));
    }
}
