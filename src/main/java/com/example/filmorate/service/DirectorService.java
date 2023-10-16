package com.example.filmorate.service;

import com.example.filmorate.dao.DirectorDao;
import com.example.filmorate.model.Director;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class DirectorService {
    private final DirectorDao directorDao;

    @Transactional
    public Director create(Director director) {
        if (director.isValid()) {
            directorDao.create(director);
        }
        return findById(director.getId());
    }

    @Transactional
    public Director update(Director director) {
        if (director.isValid()) {
            directorDao.update(director);
        }
        return findById(director.getId());
    }

    public Director findById(int id) {
        return directorDao.findById(id);
    }

    public List<Director> findAll() {
        return directorDao.findAll();
    }

    @Transactional
    public void deleteById(int id) {
        directorDao.deleteById(id);
    }
}
