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
@SequenceGenerator(allocationSize = 1, name = "reviews_id_seq")
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(generator = "reviews_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "is_positive")
    private Boolean isPositive;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "useful")
    private Integer useful;
}
