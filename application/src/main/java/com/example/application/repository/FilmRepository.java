package com.example.application.repository;

import com.example.application.entity.Film;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с фильмами.
 */
public interface FilmRepository extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {

    String NOT_FOUND = "Фильм с указанным идентификатором не найден";

    /**
     * Получение информации о фильме.
     *
     * @param id идентификатор фильма
     */
    @EntityGraph(attributePaths = {Film.Fields.mpa, Film.Fields.genres, Film.Fields.directors})
    @Override
    Optional<Film> findById(Long id);

    /**
     * Получение списка всех фильмов.
     *
     * @param sort критерий сортировки
     */
    @EntityGraph(attributePaths = {Film.Fields.mpa, Film.Fields.genres, Film.Fields.directors})
    @Override
    List<Film> findAll(Sort sort);

    /**
     * Получение списка всех фильмов.
     *
     * @param spec спецификация
     * @param sort критерий сортировки
     */
    @EntityGraph(attributePaths = {Film.Fields.mpa, Film.Fields.genres, Film.Fields.directors})
    @Override
    List<Film> findAll(@Nullable Specification<Film> spec, Sort sort);

    /**
     * Получение списка всех фильмов.
     *
     * @param ids список идентификаторов фильмов
     */
    @EntityGraph(attributePaths = {Film.Fields.mpa, Film.Fields.genres, Film.Fields.directors})
    @Override
    List<Film> findAllById(Iterable<Long> ids);

    /**
     * Получение списка всех фильмов указанного режиссёра.
     *
     * @param directorId идентификатор режиссёра
     * @param sort       критерий сортировки
     */
    @EntityGraph(attributePaths = {Film.Fields.mpa, Film.Fields.genres, Film.Fields.directors})
    List<Film> findAllByDirectors_Id(Long directorId, Sort sort);

    /**
     * Получение списка всех идентификаторов общих фильмов.
     *
     * @param userId   идентификатор пользователя
     * @param friendId идентификатор пользователя
     */
    @Query(nativeQuery = true)
    List<Long> findCommon(Long userId, Long friendId);

    /**
     * Получение списка всех идентификаторов фильмов, рекомендованных к просмотру.
     *
     * @param userId  идентификатор пользователя
     * @param userIds список идентификаторов релевантных пользователей
     */
    @Query(nativeQuery = true)
    List<Long> findRecommendations(Long userId, Iterable<Long> userIds);

    /**
     * Обновление количества положительных оценок фильмов.
     *
     * @param userId идентификатор пользователя
     */
    @Modifying
    @Query(nativeQuery = true)
    void decreaseLikesAmount(Long userId);
}
