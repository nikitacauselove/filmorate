package com.example.backend.web;

import com.example.api.FilmApi;
import com.example.api.dto.FilmDto;
import com.example.backend.repository.entity.Film;
import com.example.backend.mapper.FilmMapper;
import com.example.backend.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        Film film = filmMapper.updateFilm(filmDto, filmService.findById(filmDto.id()));

        return filmMapper.mapToFilmDto(filmService.update(film));
    }

    public FilmDto findById(Long id) {
        return filmMapper.mapToFilmDto(filmService.findById(id));
    }

    public List<FilmDto> findAll() {
        return filmMapper.mapToFilmDto(filmService.findAll());
    }

    public List<FilmDto> findAllByDirectorId(Long directorId, String sortBy) {
        return filmMapper.mapToFilmDto(filmService.findAllByDirectorId(directorId, Film.SortBy.from(sortBy.toUpperCase())));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        filmService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(Long id, Long userId) {
        filmService.addLike(id, userId);
    }

    public void deleteLike(Long id, Long userId) {
        filmService.deleteLike(id, userId);
    }

    public List<FilmDto> findCommon(Long userId, Long friendId) {
        return filmMapper.mapToFilmDto(filmService.findCommon(userId, friendId));
    }

    public List<FilmDto> findPopular(Integer count, Long genreId, Integer year) {
        return filmMapper.mapToFilmDto(filmService.findPopular(count, genreId, year));
    }

    public List<FilmDto> search(String query, List<String> by) {
        return filmMapper.mapToFilmDto(filmService.search(query, by));
    }
}
