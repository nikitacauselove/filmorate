package com.example.backend.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "films")
@SequenceGenerator(name = "films_id_seq", allocationSize = 1)
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "films_id_seq")
    private Long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;

    @OneToOne
    @JoinColumn(name = "mpa_id", referencedColumnName = "id")
    private Mpa mpa;

    private Integer likesAmount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_genres",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "film_likes",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likingUsers;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "film_directors",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors;

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
