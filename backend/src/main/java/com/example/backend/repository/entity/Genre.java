package com.example.backend.repository.entity;

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
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Setter
@SequenceGenerator(allocationSize = 1, name = "genres_id_seq")
@Table(name = "genres")
public class Genre implements Comparable<Genre> {

    @Id
    @GeneratedValue(generator = "genres_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(Genre genre) {
        return Long.compare(this.id, genre.id);
    }
}
