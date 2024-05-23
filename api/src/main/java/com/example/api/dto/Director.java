package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Director {
    private Integer id;
    private final String name;
}