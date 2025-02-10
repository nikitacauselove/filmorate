package com.example.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@FieldNameConstants
@Getter
@NoArgsConstructor
@Setter
@SequenceGenerator(name = "genre_id_seq", allocationSize = 1)
@Table(name = "genre")
public class Genre implements Comparable<Genre> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public int compareTo(Genre genre) {
        return Long.compare(this.id, genre.id);
    }
}
