package com.example.backend.web;

import com.example.api.MpaApi;
import com.example.api.dto.Mpa;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MpaController implements MpaApi {

    public Mpa findById(Integer id) {
        return Mpa.findById(id);
    }

    public List<Mpa> findAll() {
        return List.of(Mpa.values());
    }
}
