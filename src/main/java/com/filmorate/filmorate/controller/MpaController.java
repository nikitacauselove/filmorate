package com.filmorate.filmorate.controller;

import com.filmorate.filmorate.model.Mpa;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "mpa")
public class MpaController {
    @GetMapping("/{id}")
    public Mpa findById(@PathVariable int id) {
        return Mpa.findById(id);
    }

    @GetMapping
    public List<Mpa> findAll() {
        return List.of(Mpa.values());
    }
}
