package com.example.application.mapper.decorator;

import com.example.api.dto.FilmDto;
import com.example.application.mapper.FilmMapper;
import com.example.application.repository.entity.Director;
import com.example.application.repository.entity.Film;
import com.example.application.repository.entity.Genre;
import com.example.application.repository.entity.Mpa;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Set;

@Setter(onMethod_ = @Autowired)
public abstract class FilmMapperDecorator implements FilmMapper {

    private FilmMapper delegate;

    @Override
    public Film toFilm(FilmDto filmDto, Mpa mpa, Set<Genre> genres, Set<Director> directors) {
        Film film = delegate.toFilm(filmDto, mpa, genres, directors);

        film.setMpa(mpa);
        film.setGenres(genres);
        film.setDirectors(directors);
        film.setLikingUsers(Collections.emptySet());
        return film;
    }
}
