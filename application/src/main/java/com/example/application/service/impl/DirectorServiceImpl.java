package com.example.application.service.impl;

import com.example.application.domain.Director;
import com.example.application.persistence.DirectorPersistenceService;
import com.example.application.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {

    private final DirectorPersistenceService directorPersistenceService;

    @Override
    public Director create(Director director) {
        return directorPersistenceService.create(director);
    }

    @Override
    @Transactional
    public Director update(Director director) {
        return directorPersistenceService.update(director);
    }

    @Override
    public Director findById(Long id) {
        return directorPersistenceService.findById(id);
    }

    @Override
    public List<Director> findAll() {
        return directorPersistenceService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        directorPersistenceService.deleteById(id);
    }
}
