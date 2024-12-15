package com.example.application.repository.specification;

import com.example.application.repository.DirectorRepository;
import com.example.application.repository.entity.Director;
import com.example.application.repository.entity.Film;
import com.example.application.repository.entity.Genre;
import com.example.application.service.GenreService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FilmSpecification {

    private final DirectorRepository directorRepository;
    private final GenreService genreService;

    public Specification<Film> findPopular(Long genreId, Integer year) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (genreId != null) {
                Genre genre = genreService.findById(genreId);

                predicates.add(criteriaBuilder.isMember(genre, root.get("genres")));
            }
            if (year != null) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("date_part", Integer.class, criteriaBuilder.literal("year"), root.get("releaseDate")), year));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });
    }

    public Specification<Film> search(String query, List<String> by) {
        return ((root, query1, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (by.contains("director")) {
                Optional<Director> director = directorRepository.findByNameContainingIgnoreCase(query);

                director.ifPresent(value -> predicates.add(criteriaBuilder.isMember(value, root.get("directors"))));
            }
            if (by.contains("title")) {
                Predicate predicate = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), criteriaBuilder.lower(criteriaBuilder.literal("%" + query + "%"))));

                predicates.add(predicate);
            }
            return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
        });
    }
}
