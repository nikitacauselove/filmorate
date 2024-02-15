package com.example.filmorate.controller;

import com.example.filmorate.entity.Director;
import com.example.filmorate.dto.DirectorDto;
import com.example.filmorate.mapper.DirectorMapper;
import com.example.filmorate.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorMapper directorMapper;
    private final DirectorService directorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DirectorDto create(@RequestBody @Valid DirectorDto directorDto) {
        Director director = directorMapper.toDirector(directorDto);

        return directorMapper.toDirectorDto(directorService.create(director));
    }

    @PutMapping
    public DirectorDto update(@RequestBody @Valid DirectorDto directorDto) {
        Director director = directorMapper.toDirector(directorDto, directorService.findById(directorDto.getId()));

        return directorMapper.toDirectorDto(directorService.update(director));
    }

    @GetMapping("/{id}")
    public DirectorDto findById(@PathVariable int id) {
        return directorMapper.toDirectorDto(directorService.findById(id));
    }

    @GetMapping
    public List<DirectorDto> findAll() {
        return directorMapper.toDirectorDto(directorService.findAll());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        directorService.deleteById(id);
    }
}
