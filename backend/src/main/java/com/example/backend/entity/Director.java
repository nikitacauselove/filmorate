package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Director {
    private Integer id;
    private final String name;
}
