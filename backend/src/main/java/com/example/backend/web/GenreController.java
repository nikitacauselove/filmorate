package com.example.backend.web;

import com.example.api.GenreApi;
import com.example.api.dto.GenreDto;
import com.example.backend.mapper.GenreMapper;
import com.example.backend.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController implements GenreApi {

    private final GenreMapper genreMapper;
    private final GenreService genreService;

    public GenreDto findById(Long id) {
        return genreMapper.toGenreDto(genreService.findById(id));
    }

    public List<GenreDto> findAll() {
        return genreMapper.toGenreDto(genreService.findAll());
    }
}
