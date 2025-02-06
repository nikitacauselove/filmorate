package com.example.application.controller;

import com.example.api.FilmApi;
import com.example.api.model.By;
import com.example.api.model.FilmDto;
import com.example.api.model.SortBy;
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
        return filmMapper.toDto(filmService.create(filmDto));
    }

    @Override
    public FilmDto update(FilmDto filmDto) {
        return filmMapper.toDto(filmService.update(filmDto));
    }

    @Override
    public FilmDto findById(Long id) {
        return filmMapper.toDto(filmService.findById(id));
    }

    @Override
    public List<FilmDto> findAll() {
        return filmMapper.toDto(filmService.findAll());
    }

    @Override
    public List<FilmDto> findAllByDirectorId(Long directorId, SortBy sortBy) {
        return filmMapper.toDto(filmService.findAllByDirectorId(directorId, sortBy));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        filmService.deleteById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(Long id, Long userId) {
        filmService.addLike(id, userId);
    }

    @Override
    public void deleteLike(Long id, Long userId) {
        filmService.deleteLike(id, userId);
    }

    @Override
    public List<FilmDto> findCommon(Long userId, Long friendId) {
        return filmMapper.toDto(filmService.findCommon(userId, friendId));
    }

    @Override
    public List<FilmDto> findPopular(Integer count, Long genreId, Integer year) {
        return filmMapper.toDto(filmService.findPopular(count, genreId, year));
    }

    @Override
    public List<FilmDto> search(String query, List<By> by) {
        return filmMapper.toDto(filmService.search(query, by));
    }
}
