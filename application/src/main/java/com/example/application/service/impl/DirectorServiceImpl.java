package com.example.application.service.impl;

import com.example.api.model.DirectorDto;
import com.example.application.entity.Director;
import com.example.application.exception.NotFoundException;
import com.example.application.mapper.DirectorMapper;
import com.example.application.repository.DirectorRepository;
import com.example.application.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorMapper directorMapper;
    private final DirectorRepository directorRepository;

    @Override
    public Director create(Director director) {
        return directorRepository.save(director);
    }

    @Override
    @Transactional
    public Director update(DirectorDto directorDto) {
        Director director = findById(directorDto.id());

        return directorMapper.updateEntity(directorDto, director);
    }

    @Override
    public Director findById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(DirectorRepository.NOT_FOUND));
    }

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }
}
