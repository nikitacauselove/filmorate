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
@SequenceGenerator(name = "genres_id_seq", allocationSize = 1)
@Table(name = "genres")
public class Genre implements Comparable<Genre> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genres_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(Genre genre) {
        return Long.compare(this.id, genre.id);
    }
}
