package com.example.backend.service.impl;

import com.example.api.dto.DirectorDto;
import com.example.backend.mapper.DirectorMapper;
import com.example.backend.repository.entity.Director;
import com.example.backend.repository.DirectorRepository;
import com.example.backend.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorMapper directorMapper;
    private final DirectorRepository directorRepository;

    @Override
    @Transactional
    public Director create(DirectorDto directorDto) {
        Director director = directorMapper.toDirectorIgnoreId(directorDto);

        return directorRepository.save(director);
    }

    @Override
    @Transactional
    public Director update(DirectorDto directorDto) {
        Director director = findById(directorDto.id());

        return directorMapper.updateDirector(directorDto, director);
    }

    @Override
    @Transactional(readOnly = true)
    public Director findById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Режиссёр с указанным идентификатором не найден."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Director> findAllById(Iterable<Long> ids) {
        return directorRepository.findAllById(ids);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }
}
