package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Director {
    private Long id;
    private final String name;
}
