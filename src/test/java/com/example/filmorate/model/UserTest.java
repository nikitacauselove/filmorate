package com.example.filmorate.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;

class UserTest {
    @Test
    public void invalidEmail() {
        User user = new User(null, "mail.ru", "dolore ullamco", "", LocalDate.parse("1980-08-20"), Collections.emptyList());

        Assertions.assertThrows(ResponseStatusException.class, user::isValid);
    }

    @Test
    public void invalidLogin() {
        User user = new User(null, "yandex@mail.ru", "dolore ullamco", null, LocalDate.parse("2446-08-20"), Collections.emptyList());

        Assertions.assertThrows(ResponseStatusException.class, user::isValid);
    }

    @Test
    public void invalidBirthday() {
        User user = new User(null, "test@mail.ru", "dolore", "", LocalDate.parse("2446-08-20"), Collections.emptyList());

        Assertions.assertThrows(ResponseStatusException.class, user::isValid);
    }
}
