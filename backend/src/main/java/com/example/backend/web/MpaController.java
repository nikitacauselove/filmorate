package com.example.backend.web;

import com.example.api.MpaApi;
import com.example.api.dto.MpaDto;
import com.example.backend.mapper.MpaMapper;
import com.example.backend.service.MpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MpaController implements MpaApi {

    private final MpaMapper mpaMapper;
    private final MpaService mpaService;

    public MpaDto findById(Long id) {
        return mpaMapper.toMpaDto(mpaService.findById(id));
    }

    public List<MpaDto> findAll() {
        return mpaMapper.toMpaDto(mpaService.findAll());
    }
}
