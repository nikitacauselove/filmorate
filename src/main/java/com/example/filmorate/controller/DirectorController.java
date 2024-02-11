package com.example.filmorate.controller;

import com.example.filmorate.entity.Director;
import com.example.filmorate.dto.DirectorDto;
import com.example.filmorate.mapper.DirectorMapper;
import com.example.filmorate.service.impl.DirectorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "directors")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorServiceImpl directorService;

    @PostMapping
    public DirectorDto create(@RequestBody @Valid DirectorDto directorDto) {
        Director director = DirectorMapper.toDirector(directorDto);

        return DirectorMapper.toDirectorDto(directorService.create(director));
    }

    @PutMapping
    public DirectorDto update(@RequestBody @Valid DirectorDto directorDto) {
        Director director = DirectorMapper.toDirector(directorDto, directorService.findById(directorDto.getId()));

        return DirectorMapper.toDirectorDto(directorService.update(director));
    }

    @GetMapping("/{id}")
    public DirectorDto findById(@PathVariable int id) {
        return DirectorMapper.toDirectorDto(directorService.findById(id));
    }

    @GetMapping
    public List<DirectorDto> findAll() {
        return DirectorMapper.toDirectorDto(directorService.findAll());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        directorService.deleteById(id);
    }
}
