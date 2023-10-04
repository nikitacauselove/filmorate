package com.filmorate.filmorate.controller;

import com.filmorate.filmorate.model.Film;
import com.filmorate.filmorate.model.dto.FilmDto;
import com.filmorate.filmorate.model.mapper.FilmMapper;
import com.filmorate.filmorate.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "films")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmController {
    private final FilmService filmService;

    @PostMapping
    public FilmDto create(@RequestBody @Valid FilmDto filmDto) {
        Film film = FilmMapper.toFilm(filmDto);

        return FilmMapper.toFilmDto(filmService.create(film));
    }

    @PutMapping
    public FilmDto update(@RequestBody @Valid FilmDto filmDto) {
        Film film = FilmMapper.toFilm(filmDto, filmService.findById(filmDto.getId()));

        return FilmMapper.toFilmDto(filmService.update(film));
    }

    @GetMapping("/{id}")
    public FilmDto findById(@PathVariable int id) {
        return FilmMapper.toFilmDto(filmService.findById(id));
    }

    @GetMapping
    public List<FilmDto> findAll() {
        return FilmMapper.toFilmDto(filmService.findAll());
    }

    @GetMapping("/director/{directorId}")
    public List<FilmDto> findAllByDirectorId(@PathVariable int directorId, @RequestParam String sortBy) {
        return FilmMapper.toFilmDto(filmService.findAllByDirectorId(directorId, Film.SortBy.from(sortBy.toUpperCase())));
    }

    @DeleteMapping("/{filmId}")
    public void deleteById(@PathVariable int filmId) {
        filmService.deleteById(filmId);
    }

    @PutMapping("/{id}/like/{userId}")
    public FilmDto addLike(@PathVariable int id, @PathVariable int userId) {
        filmService.addLike(id, userId);

        return FilmMapper.toFilmDto(filmService.findById(id));
    }

    @DeleteMapping("/{id}/like/{userId}")
    public FilmDto deleteLike(@PathVariable int id, @PathVariable int userId) {
        filmService.deleteLike(id, userId);

        return FilmMapper.toFilmDto(filmService.findById(id));
    }

    @GetMapping("/common")
    public List<FilmDto> findCommon(@RequestParam int userId, @RequestParam int friendId) {
        return FilmMapper.toFilmDto(filmService.findCommon(userId, friendId));
    }

    @GetMapping("/popular")
    public List<FilmDto> findPopular(@RequestParam(defaultValue = "10") int count, @RequestParam Optional<Integer> genreId, @RequestParam Optional<Integer> year) {
        return FilmMapper.toFilmDto(filmService.findPopular(count, genreId, year));
    }

    @GetMapping("/search")
    public List<FilmDto> search(@RequestParam String query, @RequestParam List<String> by) {
        return FilmMapper.toFilmDto(filmService.search(query, by));
    }
}
