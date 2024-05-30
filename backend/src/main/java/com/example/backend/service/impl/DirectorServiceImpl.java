package com.example.backend.service.impl;

import com.example.backend.dao.DirectorDao;
import com.example.backend.entity.Director;
import com.example.backend.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorDao directorDao;

    @Override
    @Transactional
    public Director create(Director director) {
        directorDao.create(director);

        return findById(director.getId());
    }

    @Override
    @Transactional
    public Director update(Director director) {
        directorDao.update(director);

        return findById(director.getId());
    }

    @Override
    public Director findById(Long id) {
        return directorDao.findById(id);
    }

    @Override
    public List<Director> findAll() {
        return directorDao.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        directorDao.deleteById(id);
    }
}
