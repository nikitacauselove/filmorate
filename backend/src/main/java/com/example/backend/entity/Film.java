package com.example.backend.entity;

import com.example.api.dto.enums.Genre;
import com.example.api.dto.enums.Mpa;
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
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
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

    @Enumerated(EnumType.STRING)
    private Mpa mpa;

    private Integer likesAmount;

    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @JoinTable(name = "film_genres", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_likes",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> likingUsers;

    @Transient
    private List<Director> directors;

    public enum SortBy {
        YEAR,
        LIKES;

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
