package com.example.application.controller;

import com.example.api.DirectorApi;
import com.example.api.model.DirectorDto;
import com.example.application.controller.mapper.DirectorDtoMapper;
import com.example.application.domain.Director;
import com.example.application.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DirectorController implements DirectorApi {

    private final DirectorDtoMapper directorDtoMapper;
    private final DirectorService directorService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public DirectorDto create(DirectorDto directorDto) {
        Director director = directorDtoMapper.toDomain(directorDto);

        return directorDtoMapper.toDto(directorService.create(director));
    }

    @Override
    public DirectorDto update(DirectorDto directorDto) {
        Director director = directorDtoMapper.toDomain(directorDto);

        return directorDtoMapper.toDto(directorService.update(director));
    }

    @Override
    public DirectorDto findById(Long id) {
        return directorDtoMapper.toDto(directorService.findById(id));
    }

    @Override
    public List<DirectorDto> findAll() {
        return directorDtoMapper.toDto(directorService.findAll());
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        directorService.deleteById(id);
    }
}
