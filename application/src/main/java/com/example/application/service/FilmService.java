package com.example.application.service;

import com.example.application.domain.By;
import com.example.application.domain.Film;
import com.example.application.domain.SortBy;

import java.util.List;

/**
 * Сервис для взаимодействия с фильмами.
 */
public interface FilmService {

    /**
     * Добавление нового фильма.
     *
     * @param film информация о фильме
     */
    Film create(Film film);

    /**
     * Обновление информации о фильме.
     *
     * @param film информация о фильме
     */
    Film update(Film film);

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
     * Добавление положительной оценки.
     *
     * @param id     идентификатор фильма
     * @param userId идентификатор пользователя
     */
    void addLike(Long id, Long userId);

    /**
     * Удаление положительной оценки.
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
     * @param count   количество элементов для отображения
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
