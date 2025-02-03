package com.example.application.persistence.impl;

import com.example.application.domain.Mpa;
import com.example.application.exception.NotFoundException;
import com.example.application.persistence.MpaPersistenceService;
import com.example.application.persistence.mapper.MpaEntityMapper;
import com.example.application.persistence.model.MpaEntity;
import com.example.application.persistence.repository.MpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MpaPersistenceServiceImpl implements MpaPersistenceService {

    private final MpaEntityMapper mpaEntityMapper;
    private final MpaRepository mpaRepository;

    @Override
    public Mpa findById(Long id) {
        MpaEntity mpaEntity = mpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MpaRepository.NOT_FOUND));

        return mpaEntityMapper.toDomain(mpaEntity);
    }

    @Override
    public List<Mpa> findAll() {
        return mpaEntityMapper.toDomain(mpaRepository.findAll());
    }
}
