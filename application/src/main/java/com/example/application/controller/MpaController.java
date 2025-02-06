package com.example.application.controller;

import com.example.api.MpaApi;
import com.example.api.model.MpaDto;
import com.example.application.mapper.MpaMapper;
import com.example.application.service.MpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MpaController implements MpaApi {

    private final MpaMapper mpaMapper;
    private final MpaService mpaService;

    @Override
    public MpaDto findById(Long id) {
        return mpaMapper.toDto(mpaService.findById(id));
    }

    @Override
    public List<MpaDto> findAll() {
        return mpaMapper.toDto(mpaService.findAll());
    }
}
