package com.example.backend.service;

import com.example.backend.repository.entity.Mpa;

import java.util.List;

public interface MpaService {

    Mpa findById(Long id);

    List<Mpa> findAll();
}
