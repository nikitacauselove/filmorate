package com.example.application.service.impl;

import com.example.application.entity.Mpa;
import com.example.application.exception.NotFoundException;
import com.example.application.repository.MpaRepository;
import com.example.application.service.MpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MpaServiceImpl implements MpaService {

    private final MpaRepository mpaRepository;

    @Override
    public Mpa findById(Long id) {
        return mpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MpaRepository.NOT_FOUND));
    }

    @Override
    public List<Mpa> findAll() {
        return mpaRepository.findAll();
    }
}
