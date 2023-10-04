package com.filmorate.filmorate.controller;

import com.filmorate.filmorate.model.Director;
import com.filmorate.filmorate.model.dto.DirectorDto;
import com.filmorate.filmorate.model.mapper.DirectorMapper;
import com.filmorate.filmorate.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "directors")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DirectorController {
    private final DirectorService directorService;

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
