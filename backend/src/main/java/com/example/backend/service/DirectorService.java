package com.example.backend.service;

import com.example.api.dto.DirectorDto;
import com.example.backend.repository.entity.Director;

import java.util.List;

public interface DirectorService {

    Director create(DirectorDto directorDto);

    Director update(DirectorDto directorDto);

    Director findById(Long id);

    List<Director> findAll();

    List<Director> findAllById(Iterable<Long> ids);

    void deleteById(Long id);
}
