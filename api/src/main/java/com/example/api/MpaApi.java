package com.example.api;

import com.example.api.dto.Mpa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/mpa")
@Tag(name = "MPAA", description = "Взаимодействие с системой рейтингов Американской киноассоциации")
public interface MpaApi {

    @GetMapping("/{id}")
    @Operation(description = "Получение информации о MPAA")
    Mpa findById(
            @PathVariable Integer id
    );

    @GetMapping
    @Operation(description = "Получение списка всех MPAA")
    List<Mpa> findAll();
}
