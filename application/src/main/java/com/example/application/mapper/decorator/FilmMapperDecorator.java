package com.example.application.mapper.decorator;

import com.example.api.dto.DirectorDto;
import com.example.api.dto.FilmDto;
import com.example.api.dto.GenreDto;
import com.example.api.dto.MpaDto;
import com.example.application.mapper.FilmMapper;
import com.example.application.repository.entity.Director;
import com.example.application.repository.entity.Film;
import com.example.application.repository.entity.Genre;
import com.example.application.repository.entity.Mpa;
import com.example.application.service.DirectorService;
import com.example.application.service.GenreService;
import com.example.application.service.MpaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class FilmMapperDecorator implements FilmMapper {

    private DirectorService directorService;
    private FilmMapper delegate;
    private GenreService genreService;
    private MpaService mpaService;

    @Autowired
    public void setDirectorService(DirectorService directorService) {
        this.directorService = directorService;
    }

    @Autowired
    public void setDelegate(FilmMapper delegate) {
        this.delegate = delegate;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setMpaService(MpaService mpaService) {
        this.mpaService = mpaService;
    }

    @Override
    public Film toFilm(FilmDto filmDto) {
        Film film = delegate.toFilm(filmDto);

        film.setMpa(findMpaById(filmDto.mpa()));
        film.setGenres(findAllGenresById(filmDto.genres()));
        film.setLikingUsers(Collections.emptySet());
        film.setDirectors(findAllDirectorsById(filmDto.directors()));
        return film;
    }

    @Override
    public Film updateFilm(FilmDto filmDto, Film film) {
        Film updatedFilm = delegate.updateFilm(filmDto, film);

        updatedFilm.setMpa(findMpaById(filmDto.mpa()));
        updatedFilm.setGenres(findAllGenresById(filmDto.genres()));
        updatedFilm.setDirectors(findAllDirectorsById(filmDto.directors()));
        return updatedFilm;
    }

    private Mpa findMpaById(MpaDto mpaDto) {
        return mpaService.findById(mpaDto.id());
    }

    private Set<Genre> findAllGenresById(Set<GenreDto> genreDtoSet) {
        List<Long> ids = genreDtoSet == null ? Collections.emptyList() : genreDtoSet.stream().map(GenreDto::id).toList();

        return new HashSet<>(genreService.findAllById(ids));
    }

    private Set<Director> findAllDirectorsById(Set<DirectorDto> directorDtoSet) {
        List<Long> ids = directorDtoSet == null ? Collections.emptyList() : directorDtoSet.stream().map(DirectorDto::id).toList();

        return new HashSet<>(directorService.findAllById(ids));
    }
}
