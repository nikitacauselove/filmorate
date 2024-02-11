package com.example.filmorate.dao;

import com.example.filmorate.entity.Film;
import com.example.filmorate.entity.Genre;
import com.example.filmorate.entity.Mpa;
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
public class FilmDaoTest {
    private final FilmDao filmDao;

    private static final Film FIRST_FILM = new Film(1, "Film Updated", "New film update decription", LocalDate.parse("1989-04-17"), 190, Mpa.NC17, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    private static final Film SECOND_FILM = new Film(2, "New film", "New film about friends", LocalDate.parse("1999-04-30"), 120, Mpa.NC17, List.of(Genre.COMEDY, Genre.DRAMA), Collections.emptyList(), Collections.emptyList());

    @Test
    public void findAll() {
        Assertions.assertEquals(filmDao.findAll(), List.of(FIRST_FILM, SECOND_FILM));
    }

    @Test
    public void findFilmById() {
        Assertions.assertEquals(filmDao.findById(SECOND_FILM.getId()), SECOND_FILM);
    }
}
