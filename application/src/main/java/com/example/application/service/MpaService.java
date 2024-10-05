package com.example.application.service;

import com.example.application.repository.entity.Mpa;

import java.util.List;

public interface MpaService {

    Mpa findById(Long id);

    List<Mpa> findAll();
}
