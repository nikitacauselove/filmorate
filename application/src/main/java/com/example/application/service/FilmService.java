package com.example.application.service;

import com.example.api.model.By;
import com.example.api.model.FilmDto;
import com.example.api.model.SortBy;
import com.example.application.entity.Film;

import java.util.List;

/**
 * Сервис для взаимодействия с фильмами.
 */
public interface FilmService {

    /**
     * Добавление нового фильма.
     *
     * @param filmDto информация о фильме
     */
    Film create(FilmDto filmDto);

    /**
     * Обновление информации о фильме.
     *
     * @param filmDto информация о фильме
     */
    Film update(FilmDto filmDto);

    /**
     * Получение информации о фильме.
     *
     * @param id идентификатор фильма
     */
    Film findById(Long id);

    /**
     * Получение списка всех фильмов.
     */
    List<Film> findAll();

    /**
     * Получение списка всех фильмов указанного режиссёра.
     *
     * @param directorId идентификатор режиссёра
     * @param sortBy     критерий сортировки фильмов
     */
    List<Film> findAllByDirectorId(Long directorId, SortBy sortBy);

    /**
     * Удаление фильма.
     *
     * @param id идентификатор фильма
     */
    void deleteById(Long id);

    /**
     * Добавление или удаление оценки.
     *
     * @param id     идентификатор фильма
     * @param userId идентификатор пользователя
     */
    void addLike(Long id, Long userId);

    /**
     * Добавление или удаление оценки.
     *
     * @param id     идентификатор фильма
     * @param userId идентификатор пользователя
     */
    void deleteLike(Long id, Long userId);

    /**
     * Получение списка всех общих фильмов.
     *
     * @param userId   идентификатор пользователя
     * @param friendId идентификатор пользователя
     */
    List<Film> findCommon(Long userId, Long friendId);

    /**
     * Получение списка всех популярных фильмов.
     *
     * @param count   максимальное количество элементов
     * @param genreId идентификатор жанра фильма
     * @param year    год выхода фильма
     */
    List<Film> findPopular(Integer count, Long genreId, Integer year);

    /**
     * Получение списка всех фильмов, рекомендованных к просмотру.
     *
     * @param userId идентификатор пользователя
     */
    List<Film> findRecommendations(Long userId);

    /**
     * Поиск фильмов.
     *
     * @param query текст для поиска фильмов
     * @param by    список критериев для поиска фильмов
     */
    List<Film> search(String query, List<By> by);
}
