package com.example.application.persistence.impl;

import com.example.application.domain.Director;
import com.example.application.persistence.DirectorPersistenceService;
import com.example.application.persistence.mapper.DirectorEntityMapper;
import com.example.application.persistence.model.DirectorEntity;
import com.example.application.persistence.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, DirectorRepository.NOT_FOUND));
        DirectorEntity updatedEntity = directorEntityMapper.updateEntity(director, directorEntity);

        return directorEntityMapper.toDomain(updatedEntity);
    }

    @Override
    public Director findById(Long id) {
        DirectorEntity directorEntity = directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, DirectorRepository.NOT_FOUND));

        return directorEntityMapper.toDomain(directorEntity);
    }

    @Override
    public List<Director> findAll() {
        return directorEntityMapper.toDomain(directorRepository.findAll());
    }

    @Override
    public List<Director> findAllById(Iterable<Long> ids) {
        return directorEntityMapper.toDomain(directorRepository.findAllById(ids));
    }

    @Override
    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return directorRepository.existsById(id);
    }
}
