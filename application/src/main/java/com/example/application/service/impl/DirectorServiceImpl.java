package com.example.application.service.impl;

import com.example.api.dto.DirectorDto;
import com.example.application.mapper.DirectorMapper;
import com.example.application.repository.entity.Director;
import com.example.application.repository.DirectorRepository;
import com.example.application.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorMapper directorMapper;
    private final DirectorRepository directorRepository;

    @Override
    public Director create(DirectorDto directorDto) {
        Director director = directorMapper.toDirector(directorDto);

        return directorRepository.save(director);
    }

    @Override
    public Director update(DirectorDto directorDto) {
        Director director = findById(directorDto.id());
        Director updatedDirector = directorMapper.updateDirector(directorDto, director);

        return directorRepository.save(updatedDirector);
    }

    @Override
    public Director findById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Режиссёр с указанным идентификатором не найден"));
    }

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public List<Director> findAllById(Iterable<Long> ids) {
        return directorRepository.findAllById(ids);
    }

    @Override
    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }
}
