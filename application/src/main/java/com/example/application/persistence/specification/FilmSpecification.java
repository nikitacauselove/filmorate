package com.example.application.persistence.specification;

import com.example.application.domain.By;
import com.example.application.persistence.model.DirectorEntity;
import com.example.application.persistence.model.FilmEntity;
import com.example.application.persistence.repository.DirectorRepository;
import com.example.application.persistence.repository.GenreRepository;
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

    public Specification<FilmEntity> byGenres(Long genreId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (genreId != null) {
                return criteriaBuilder.isMember(genreRepository.getReferenceById(genreId), root.get(FilmEntity.Fields.genres));
            }
            return criteriaBuilder.conjunction();
        };
    }

    public Specification<FilmEntity> byReleaseDate(Integer year) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (year != null) {
                return criteriaBuilder.equal(criteriaBuilder.function(DATE_PART, Integer.class, criteriaBuilder.literal(YEAR), root.get(FilmEntity.Fields.releaseDate)), year);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public Specification<FilmEntity> byDirectors(String query, List<By> by) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (by.contains(By.DIRECTOR)) {
                Optional<DirectorEntity> directorEntityOptional = directorRepository.findByNameContainingIgnoreCase(query);

                if (directorEntityOptional.isPresent()) {
                    return criteriaBuilder.isMember(directorEntityOptional.get(), root.get(FilmEntity.Fields.directors));
                }
            }
            return criteriaBuilder.disjunction();
        };
    }

    public Specification<FilmEntity> byName(String query, List<By> by) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (by.contains(By.TITLE)) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(FilmEntity.Fields.name)), "%" + query.toLowerCase() + "%");
            }
            return criteriaBuilder.disjunction();
        };
    }
}
