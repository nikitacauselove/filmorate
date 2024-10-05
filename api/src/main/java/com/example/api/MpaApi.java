package com.example.api;

import com.example.api.dto.MpaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/mpa")
@Tag(name = "MPA", description = "Взаимодействие с системой рейтингов Американской киноассоциации")
public interface MpaApi {

    @GetMapping("/{id}")
    @Operation(description = "Получение информации о MPA")
    MpaDto findById(@PathVariable Long id);

    @GetMapping
    @Operation(description = "Получение списка всех MPA")
    List<MpaDto> findAll();
}
