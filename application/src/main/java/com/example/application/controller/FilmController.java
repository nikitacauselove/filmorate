package com.example.application.controller;

import com.example.api.FilmApi;
import com.example.api.dto.FilmDto;
import com.example.api.dto.enums.Operation;
import com.example.api.dto.enums.SortBy;
import com.example.application.mapper.FilmMapper;
import com.example.application.service.FilmService;
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

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto create(FilmDto filmDto) {
        return filmMapper.toFilmDto(filmService.create(filmDto));
    }

    @Override
    public FilmDto update(FilmDto filmDto) {
        return filmMapper.toFilmDto(filmService.update(filmDto));
    }

    @Override
    public FilmDto findById(Long id) {
        return filmMapper.toFilmDto(filmService.findById(id));
    }

    @Override
    public List<FilmDto> findAll() {
        return filmMapper.toFilmDto(filmService.findAll());
    }

    @Override
    public List<FilmDto> findAllByDirectorId(Long directorId, SortBy sortBy) {
        return filmMapper.toFilmDto(filmService.findAllByDirectorId(directorId, sortBy));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        filmService.deleteById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(Long id, Long userId) {
        filmService.addOrDeleteLike(id, userId, Operation.ADD);
    }

    @Override
    public void deleteLike(Long id, Long userId) {
        filmService.addOrDeleteLike(id, userId, Operation.REMOVE);
    }

    @Override
    public List<FilmDto> findCommon(Long userId, Long friendId) {
        return filmMapper.toFilmDto(filmService.findCommon(userId, friendId));
    }

    @Override
    public List<FilmDto> findPopular(Integer count, Long genreId, Integer year) {
        return filmMapper.toFilmDto(filmService.findPopular(count, genreId, year));
    }

    @Override
    public List<FilmDto> search(String query, List<String> by) {
        return filmMapper.toFilmDto(filmService.search(query, by));
    }
}
