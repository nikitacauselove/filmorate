package com.example.application.service;

import com.example.api.dto.DirectorDto;
import com.example.application.repository.entity.Director;

import java.util.List;

/**
 * Сервис для взаимодействия с режиссёрами.
 */
public interface DirectorService {

    /**
     * Добавление нового режиссёра.
     * @param directorDto информация о режиссёре
     */
    Director create(DirectorDto directorDto);

    /**
     * Обновление информации о режиссёре.
     * @param directorDto информация о режиссёре
     */
    Director update(DirectorDto directorDto);

    /**
     * Получение информации о режиссёре.
     * @param id идентификатор режиссёра
     */
    Director findById(Long id);

    /**
     * Получение списка всех режиссёров.
     */
    List<Director> findAll();

    /**
     * Получение списка всех режиссёров с заданными идентификаторами.
     * @param ids список идентификаторов режиссёров
     */
    List<Director> findAllById(Iterable<Long> ids);

    /**
     * Удаление режиссёра.
     * @param id идентификатор режиссёра
     */
    void deleteById(Long id);
}
