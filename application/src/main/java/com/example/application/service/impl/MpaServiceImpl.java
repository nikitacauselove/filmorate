package com.example.application.service.impl;

import com.example.application.repository.MpaRepository;
import com.example.application.repository.entity.Mpa;
import com.example.application.service.MpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MpaServiceImpl implements MpaService {

    private final MpaRepository mpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Mpa findById(Long id) {
        return mpaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Рейтинг Американской киноассоциации с указанным идентификатором не найден"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mpa> findAll() {
        return mpaRepository.findAll();
    }
}
