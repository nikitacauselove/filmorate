package com.example.application.repository.specification;

import com.example.api.dto.enums.By;
import com.example.application.repository.DirectorRepository;
import com.example.application.repository.GenreRepository;
import com.example.application.repository.entity.Film;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FilmSpecification {

    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;

    public Specification<Film> findPopular(Long genreId, Integer year) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (genreId != null) {
                genreRepository.findById(genreId).ifPresent(genre -> predicates.add(criteriaBuilder.isMember(genre, root.get(Film.Fields.genres))));
            }
            if (year != null) {
                Predicate predicate = criteriaBuilder.equal(criteriaBuilder.function("date_part", Integer.class, criteriaBuilder.literal("year"), root.get(Film.Fields.releaseDate)), year);

                predicates.add(predicate);
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

    public Specification<Film> search(String query, List<By> by) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (by.contains(By.DIRECTOR)) {
                directorRepository.findByNameContainingIgnoreCase(query).ifPresent(director -> predicates.add(criteriaBuilder.isMember(director, root.get(Film.Fields.directors))));
            }
            if (by.contains(By.TITLE)) {
                Predicate predicate = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get(Film.Fields.name)), criteriaBuilder.lower(criteriaBuilder.literal("%" + query + "%"))));

                predicates.add(predicate);
            }
            return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
        };
    }
}
