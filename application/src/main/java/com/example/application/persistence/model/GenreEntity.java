package com.example.application.persistence.model;

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
@SequenceGenerator(name = "genre_id_seq", allocationSize = 1)
@Table(name = "genre")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

}
