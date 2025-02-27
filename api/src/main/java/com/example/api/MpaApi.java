package com.example.api;

import com.example.api.model.MpaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/mpa")
@Tag(name = "Рейтинги Американской киноассоциации", description = "Взаимодействие с рейтингами Американской киноассоциации")
public interface MpaApi {

    @GetMapping("/{id}")
    @Operation(description = "Получение информации о рейтинге Американской киноассоциации")
    MpaDto findById(@PathVariable Long id);

    @GetMapping
    @Operation(description = "Получение списка всех рейтингов Американской киноассоциации")
    List<MpaDto> findAll();
}
