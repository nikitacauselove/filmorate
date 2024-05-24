package com.example.backend.web;

import com.example.api.FilmApi;
import com.example.api.dto.FilmDto;
import com.example.backend.entity.Film;
import com.example.backend.mapper.FilmMapper;
import com.example.backend.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FilmController implements FilmApi {

    private final FilmMapper filmMapper;
    private final FilmService filmService;

    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto create(FilmDto filmDto) {
        Film film = filmMapper.mapToFilm(filmDto);

        return filmMapper.mapToFilmDto(filmService.create(film));
    }

    public FilmDto update(FilmDto filmDto) {
        Film film = filmMapper.mapToFilm(filmDto, filmService.findById(filmDto.id()));

        return filmMapper.mapToFilmDto(filmService.update(film));
    }

    public FilmDto findById(Integer id) {
        return filmMapper.mapToFilmDto(filmService.findById(id));
    }

    public List<FilmDto> findAll() {
        return filmMapper.mapToFilmDto(filmService.findAll());
    }

    public List<FilmDto> findAllByDirectorId(Integer directorId, String sortBy) {
        return filmMapper.mapToFilmDto(filmService.findAllByDirectorId(directorId, Film.SortBy.from(sortBy.toUpperCase())));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Integer filmId) {
        filmService.deleteById(filmId);
    }

    public FilmDto addLike(Integer id, Integer userId) {
        filmService.addLike(id, userId);

        return filmMapper.mapToFilmDto(filmService.findById(id));
    }

    public FilmDto deleteLike(Integer id, Integer userId) {
        filmService.deleteLike(id, userId);

        return filmMapper.mapToFilmDto(filmService.findById(id));
    }

    public List<FilmDto> findCommon(Integer userId, Integer friendId) {
        return filmMapper.mapToFilmDto(filmService.findCommon(userId, friendId));
    }

    public List<FilmDto> findPopular(Integer count, Optional<Integer> genreId, Optional<Integer> year) {
        return filmMapper.mapToFilmDto(filmService.findPopular(count, genreId, year));
    }

    public List<FilmDto> search(String query, List<String> by) {
        return filmMapper.mapToFilmDto(filmService.search(query, by));
    }
}
