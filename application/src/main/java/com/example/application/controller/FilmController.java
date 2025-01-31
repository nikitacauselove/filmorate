package com.example.application.controller;

import com.example.api.FilmApi;
import com.example.api.model.By;
import com.example.api.model.FilmDto;
import com.example.api.model.SortBy;
import com.example.application.controller.mapper.ByMapper;
import com.example.application.controller.mapper.FilmDtoMapper;
import com.example.application.controller.mapper.SortByMapper;
import com.example.application.domain.Film;
import com.example.application.domain.Operation;
import com.example.application.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilmController implements FilmApi {

    private final ByMapper byMapper;
    private final FilmDtoMapper filmDtoMapper;
    private final FilmService filmService;
    private final SortByMapper sortByMapper;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto create(FilmDto filmDto) {
        Film film = filmDtoMapper.toDomain(filmDto);

        return filmDtoMapper.toDto(filmService.create(film));
    }

    @Override
    public FilmDto update(FilmDto filmDto) {
        Film film = filmDtoMapper.toDomain(filmDto);

        return filmDtoMapper.toDto(filmService.update(film));
    }

    @Override
    public FilmDto findById(Long id) {
        return filmDtoMapper.toDto(filmService.findById(id));
    }

    @Override
    public List<FilmDto> findAll() {
        return filmDtoMapper.toDto(filmService.findAll());
    }

    @Override
    public List<FilmDto> findAllByDirectorId(Long directorId, SortBy sortBy) {
        return filmDtoMapper.toDto(filmService.findAllByDirectorId(directorId, sortByMapper.toDomain(sortBy)));
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
        return filmDtoMapper.toDto(filmService.findCommon(userId, friendId));
    }

    @Override
    public List<FilmDto> findPopular(Integer count, Long genreId, Integer year) {
        return filmDtoMapper.toDto(filmService.findPopular(count, genreId, year));
    }

    @Override
    public List<FilmDto> search(String query, List<By> by) {
        return filmDtoMapper.toDto(filmService.search(query, byMapper.toDomain(by)));
    }
}
