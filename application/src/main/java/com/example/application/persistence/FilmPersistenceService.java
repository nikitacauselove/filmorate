package com.example.application.persistence;

import com.example.api.model.type.By;
import com.example.api.model.type.Operation;
import com.example.api.model.type.SortBy;
import com.example.application.domain.Film;

import java.util.List;

/**
 * Сервис для взаимодействия с фильмами.
 */
public interface FilmPersistenceService {

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
     * @param sortBy критерий сортировки фильмов
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
     * @param id идентификатор фильма
     * @param userId идентификатор пользователя
     * @param operation тип операции
     */
    void addOrDeleteLike(Long id, Long userId, Operation operation);

    /**
     * Получение списка всех общих фильмов.
     *
     * @param userId идентификатор пользователя
     * @param friendId идентификатор пользователя
     */
    List<Film> findCommon(Long userId, Long friendId);

    /**
     * Получение списка всех популярных фильмов.
     *
     * @param count количество элементов для отображения
     * @param genreId идентификатор жанра фильма
     * @param year год выхода фильма
     */
    List<Film> findPopular(Integer count, Long genreId, Integer year);

    /**
     * Поиск фильмов.
     *
     * @param query текст для поиска фильмов
     * @param by список критериев для поиска фильмов
     */
    List<Film> search(String query, List<By> by);
}
