package com.example.filmorate.service.impl;

import com.example.filmorate.dao.DirectorDao;
import com.example.filmorate.entity.Director;
import com.example.filmorate.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class DirectorServiceImpl implements DirectorService {
    private final DirectorDao directorDao;

    @Override
    @Transactional
    public Director create(Director director) {
        if (director.isValid()) {
            directorDao.create(director);
        }
        return findById(director.getId());
    }

    @Override
    @Transactional
    public Director update(Director director) {
        if (director.isValid()) {
            directorDao.update(director);
        }
        return findById(director.getId());
    }

    @Override
    public Director findById(int id) {
        return directorDao.findById(id);
    }

    @Override
    public List<Director> findAll() {
        return directorDao.findAll();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        directorDao.deleteById(id);
    }
}
