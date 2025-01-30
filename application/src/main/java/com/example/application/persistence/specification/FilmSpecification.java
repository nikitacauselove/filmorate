package com.example.application.persistence.specification;

import com.example.api.model.type.By;
import com.example.application.persistence.DirectorPersistenceService;
import com.example.application.persistence.GenrePersistenceService;
import com.example.application.persistence.model.FilmEntity;
import com.example.application.persistence.model.GenreEntity;
import com.example.application.persistence.repository.DirectorRepository;
import com.example.application.persistence.repository.GenreRepository;
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

    public Specification<FilmEntity> findPopular(Long genreId, Integer year) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (genreId != null) {
                genreRepository.findById(genreId).ifPresent(genre -> predicates.add(criteriaBuilder.isMember(genre, root.get(FilmEntity.Fields.genres))));
            }
            if (year != null) {
                Predicate predicate = criteriaBuilder.equal(criteriaBuilder.function("date_part", Integer.class, criteriaBuilder.literal("year"), root.get(FilmEntity.Fields.releaseDate)), year);

                predicates.add(predicate);
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

    public Specification<FilmEntity> search(String query, List<By> by) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (by.contains(By.DIRECTOR)) {
                directorRepository.findByNameContainingIgnoreCase(query).ifPresent(director -> predicates.add(criteriaBuilder.isMember(director, root.get(FilmEntity.Fields.directors))));
            }
            if (by.contains(By.TITLE)) {
                Predicate predicate = criteriaBuilder.and(criteriaBuilder.like(criteriaBuilder.lower(root.get(FilmEntity.Fields.name)), criteriaBuilder.lower(criteriaBuilder.literal("%" + query + "%"))));

                predicates.add(predicate);
            }
            return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
        };
    }
}
