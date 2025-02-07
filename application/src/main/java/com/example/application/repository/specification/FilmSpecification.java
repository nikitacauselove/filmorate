package com.example.application.repository.specification;

import com.example.api.model.By;
import com.example.application.entity.Director;
import com.example.application.entity.Film;
import com.example.application.entity.Genre;
import com.example.application.repository.DirectorRepository;
import com.example.application.repository.GenreRepository;
import jakarta.persistence.criteria.JoinType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FilmSpecification {

    private final static String DATE_PART = "DATE_PART";
    private final static String YEAR = "YEAR";

    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;

    public Specification<Film> fetchGenres(boolean isConjunction) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (criteriaQuery != null && criteriaQuery.getResultType() == Film.class) {
                root.fetch(Film.Fields.genres, JoinType.LEFT);
            }
            return isConjunction ? criteriaBuilder.conjunction() : criteriaBuilder.disjunction();
        };
    }

    public Specification<Film> fetchDirectors(boolean isConjunction) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (criteriaQuery != null && criteriaQuery.getResultType() == Film.class) {
                root.fetch(Film.Fields.directors, JoinType.LEFT);
            }
            return isConjunction ? criteriaBuilder.conjunction() : criteriaBuilder.disjunction();
        };
    }

    public Specification<Film> byGenres(Long genreId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (genreId != null) {
                Optional<Genre> genreOptional = genreRepository.findById(genreId);

                if (genreOptional.isPresent()) {
                    return criteriaBuilder.isMember(genreOptional.get(), root.get(Film.Fields.genres));
                }
            }
            return criteriaBuilder.conjunction();
        };
    }

    public Specification<Film> byReleaseDate(Integer year) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (year != null) {
                return criteriaBuilder.equal(criteriaBuilder.function(DATE_PART, Integer.class, criteriaBuilder.literal(YEAR), root.get(Film.Fields.releaseDate)), year);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public Specification<Film> byDirectors(String query, List<By> by) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (by.contains(By.DIRECTOR)) {
                Optional<Director> directorOptional = directorRepository.findByNameContainingIgnoreCase(query);

                if (directorOptional.isPresent()) {
                    return criteriaBuilder.isMember(directorOptional.get(), root.get(Film.Fields.directors));
                }
            }
            return criteriaBuilder.disjunction();
        };
    }

    public Specification<Film> byName(String query, List<By> by) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (by.contains(By.TITLE)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(Film.Fields.name)), "%" + query.toLowerCase() + "%");
            }
            return criteriaBuilder.disjunction();
        };
    }
}
