package com.example.application.service.impl;

import com.example.application.domain.Mpa;
import com.example.application.persistence.MpaPersistenceService;
import com.example.application.service.MpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MpaServiceImpl implements MpaService {

    private final MpaPersistenceService mpaPersistenceService;

    @Override
    public Mpa findById(Long id) {
        return mpaPersistenceService.findById(id);
    }

    @Override
    public List<Mpa> findAll() {
        return mpaPersistenceService.findAll();
    }
}
