package com.example.backend.web;

import com.example.api.DirectorApi;
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
        return directorMapper.toDirectorDto(directorService.create(directorDto));
    }

    public DirectorDto update(DirectorDto directorDto) {
        return directorMapper.toDirectorDto(directorService.update(directorDto));
    }

    public DirectorDto findById(Long id) {
        return directorMapper.toDirectorDto(directorService.findById(id));
    }

    public List<DirectorDto> findAll() {
        return directorMapper.toDirectorDto(directorService.findAll());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {
        directorService.deleteById(id);
    }
}
