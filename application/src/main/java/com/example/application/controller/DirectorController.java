package com.example.application.controller;

import com.example.api.DirectorApi;
import com.example.api.dto.DirectorDto;
import com.example.application.mapper.DirectorMapper;
import com.example.application.repository.entity.Director;
import com.example.application.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DirectorController implements DirectorApi {

    private final DirectorMapper directorMapper;
    private final DirectorService directorService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public DirectorDto create(DirectorDto directorDto) {
        Director director = directorMapper.toDirector(directorDto);

        return directorMapper.toDirectorDto(directorService.create(director));
    }

    @Override
    public DirectorDto update(DirectorDto directorDto) {
        Director director = directorMapper.updateDirector(directorDto, directorService.findById(directorDto.id()));

        return directorMapper.toDirectorDto(directorService.update(director));
    }

    @Override
    public DirectorDto findById(Long id) {
        return directorMapper.toDirectorDto(directorService.findById(id));
    }

    @Override
    public List<DirectorDto> findAll() {
        return directorMapper.toDirectorDto(directorService.findAll());
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        directorService.deleteById(id);
    }
}
