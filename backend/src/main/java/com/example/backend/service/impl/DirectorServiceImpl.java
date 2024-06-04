package com.example.backend.service.impl;

import com.example.backend.entity.Director;
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

    private final DirectorRepository directorRepository;

    @Override
    @Transactional
    public Director create(Director director) {
        directorRepository.save(director);

        return findById(director.getId());
    }

    @Override
    @Transactional
    public Director update(Director director) {
        directorRepository.save(director);

        return findById(director.getId());
    }

    @Override
    @Transactional
    public Director findById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Режиссёр с указанным идентификатором не найден."));
    }

    @Override
    @Transactional
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        directorRepository.deleteById(id);
    }
}
