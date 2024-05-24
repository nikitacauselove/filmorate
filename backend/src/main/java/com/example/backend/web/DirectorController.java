package com.example.backend.web;

import com.example.api.DirectorApi;
import com.example.api.dto.Director;
import com.example.api.dto.DirectorDto;
import com.example.backend.mapper.DirectorMapper;
import com.example.backend.service.DirectorService;
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

    @ResponseStatus(HttpStatus.CREATED)
    public DirectorDto create(DirectorDto directorDto) {
        Director director = directorMapper.mapToDirector(directorDto);

        return directorMapper.mapToDirectorDto(directorService.create(director));
    }

    public DirectorDto update(DirectorDto directorDto) {
        Director director = directorMapper.mapToDirector(directorDto, directorService.findById(directorDto.id()));

        return directorMapper.mapToDirectorDto(directorService.update(director));
    }

    public DirectorDto findById(Integer id) {
        return directorMapper.mapToDirectorDto(directorService.findById(id));
    }

    public List<DirectorDto> findAll() {
        return directorMapper.mapToDirectorDto(directorService.findAll());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Integer id) {
        directorService.deleteById(id);
    }
}
