package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "directors")
@SequenceGenerator(name = "directors_id_seq", allocationSize = 1)
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "directors_id_seq")
    private Long id;
    private String name;
}
