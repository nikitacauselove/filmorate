package com.example.application.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;

@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@FieldNameConstants
@Getter
@NoArgsConstructor
@Setter
@SequenceGenerator(name = "film_id_seq", allocationSize = 1)
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "duration")
    private Integer duration;

    @JoinColumn(name = "mpa_id")
    @OneToOne
    private Mpa mpa;

    @JoinTable(name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private SortedSet<Genre> genres;

    @JoinTable(name = "film_director",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Director> directors;

    @JoinTable(name = "film_like",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> likingUsers;

    @Column(name = "likes_amount")
    private Integer likesAmount;
}
