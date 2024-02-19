package com.example.filmorate.controller;

import com.example.filmorate.mapper.FilmMapper;
import com.example.filmorate.service.FilmService;
import com.example.filmorate.entity.Film;
import com.example.filmorate.dto.FilmDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmMapper filmMapper;
    private final FilmService filmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto create(@RequestBody @Valid FilmDto filmDto) {
        Film film = filmMapper.toFilm(filmDto);

        return filmMapper.toFilmDto(filmService.create(film));
    }

    @PutMapping
    public FilmDto update(@RequestBody @Valid FilmDto filmDto) {
        Film film = filmMapper.toFilm(filmDto, filmService.findById(filmDto.getId()));

        return filmMapper.toFilmDto(filmService.update(film));
    }

    @GetMapping("/{id}")
    public FilmDto findById(@PathVariable int id) {
        return filmMapper.toFilmDto(filmService.findById(id));
    }

    @GetMapping
    public List<FilmDto> findAll() {
        return filmMapper.toFilmDto(filmService.findAll());
    }

    @GetMapping("/director/{directorId}")
    public List<FilmDto> findAllByDirectorId(@PathVariable int directorId, @RequestParam String sortBy) {
        return filmMapper.toFilmDto(filmService.findAllByDirectorId(directorId, Film.SortBy.from(sortBy.toUpperCase())));
    }

    @DeleteMapping("/{filmId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int filmId) {
        filmService.deleteById(filmId);
    }

    @PutMapping("/{id}/like/{userId}")
    public FilmDto addLike(@PathVariable int id, @PathVariable int userId) {
        filmService.addLike(id, userId);

        return filmMapper.toFilmDto(filmService.findById(id));
    }

    @DeleteMapping("/{id}/like/{userId}")
    public FilmDto deleteLike(@PathVariable int id, @PathVariable int userId) {
        filmService.deleteLike(id, userId);

        return filmMapper.toFilmDto(filmService.findById(id));
    }

    @GetMapping("/common")
    public List<FilmDto> findCommon(@RequestParam int userId, @RequestParam int friendId) {
        return filmMapper.toFilmDto(filmService.findCommon(userId, friendId));
    }

    @GetMapping("/popular")
    public List<FilmDto> findPopular(@RequestParam(defaultValue = "10") int count, @RequestParam Optional<Integer> genreId, @RequestParam Optional<Integer> year) {
        return filmMapper.toFilmDto(filmService.findPopular(count, genreId, year));
    }

    @GetMapping("/search")
    public List<FilmDto> search(@RequestParam String query, @RequestParam List<String> by) {
        return filmMapper.toFilmDto(filmService.search(query, by));
    }
}
