package com.example.application.persistence.impl;

import com.example.application.domain.Director;
import com.example.application.exception.NotFoundException;
import com.example.application.persistence.DirectorPersistenceService;
import com.example.application.persistence.mapper.DirectorEntityMapper;
import com.example.application.persistence.model.DirectorEntity;
import com.example.application.persistence.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DirectorPersistenceServiceImpl implements DirectorPersistenceService {

    private final DirectorEntityMapper directorEntityMapper;
    private final DirectorRepository directorRepository;

    @Override
    public Director create(Director director) {
        DirectorEntity directorEntity = directorEntityMapper.toEntity(director);

        return directorEntityMapper.toDomain(directorRepository.save(directorEntity));
    }

    @Override
    @Transactional
    public Director update(Director director) {
        DirectorEntity directorEntity = directorRepository.findById(director.id())
                .orElseThrow(() -> new NotFoundException(DirectorRepository.NOT_FOUND));

        return directorEntityMapper.toDomain(directorEntityMapper.updateEntity(director, directorEntity));
    }

    @Override
    public Director findById(Long id) {
        DirectorEntity directorEntity = directorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(DirectorRepository.NOT_FOUND));

        return directorEntityMapper.toDomain(directorEntity);
    }

    @Override
    public List<Director> findAll() {
        return directorEntityMapper.toDomain(directorRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }
}
