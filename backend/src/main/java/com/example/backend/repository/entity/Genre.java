package com.example.backend.repository.entity;

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
@Table(name = "genres")
@SequenceGenerator(name = "genres_id_seq", allocationSize = 1)
public class Genre implements Comparable<Genre> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genres_id_seq")
    private Long id;
    private String name;

    @Override
    public int compareTo(Genre genre) {
        return Long.compare(this.id, genre.id);
    }
}
