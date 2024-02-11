package com.example.filmorate.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;

class FilmTest {
    @Test
    public void invalidName() {
        Film film = new Film(null, "", "Description", LocalDate.parse("1900-03-25"), 200, Mpa.NC17, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        Assertions.assertThrows(ResponseStatusException.class, film::isValid);
    }

    @Test
    public void invalidDescription() {
        Film film = new Film(null, "Film name", "Пятеро друзей ( комик-группа «Шарло»), приезжают в город Бризуль. Здесь они хотят разыскать господина Огюста Куглова, который задолжал им деньги, а именно 20 миллионов. о Куглов, который за время «своего отсутствия», стал кандидатом Коломбани.", LocalDate.parse("1900-03-25"), 200, Mpa.NC17, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        Assertions.assertThrows(ResponseStatusException.class, film::isValid);
    }

    @Test
    public void invalidReleaseDate() {
        Film film = new Film(null, "Name", "Description", LocalDate.parse("1890-03-25"), 200, Mpa.NC17, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        Assertions.assertThrows(ResponseStatusException.class, film::isValid);
    }

    @Test
    public void invalidDuration() {
        Film film = new Film(null, "Name", "Descrition", LocalDate.parse("1980-03-25"), -200, Mpa.PG13, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());

        Assertions.assertThrows(ResponseStatusException.class, film::isValid);
    }
}
