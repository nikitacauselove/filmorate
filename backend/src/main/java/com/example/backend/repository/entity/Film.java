package com.example.backend.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"genres", "likingUsers", "directors"})
@Getter
@NoArgsConstructor
@Setter
@SequenceGenerator(allocationSize = 1, name = "films_id_seq")
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(generator = "films_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "duration")
    private Integer duration;

    @OneToOne
    @JoinColumn(name = "mpa_id", referencedColumnName = "id")
    private Mpa mpa;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @OrderBy("name")
    private Set<Genre> genres;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "film_directors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "film_likes",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likingUsers;

    @Column(name = "likesAmount")
    private Integer likesAmount;

    @Getter
    @RequiredArgsConstructor
    public enum SortBy {
        YEAR("releaseDate"),
        LIKES("likesAmount");

        private final String property;

        public static SortBy from(String string) {
            for (SortBy value : SortBy.values()) {
                if (value.name().equals(string)) {
                    return value;
                }
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Указанный тип сортировки не найден.");
        }
    }
}
