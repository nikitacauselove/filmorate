package com.example.application.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@Setter
@SequenceGenerator(name = "directors_id_seq", allocationSize = 1)
@Table(name = "directors")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "directors_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;
}
