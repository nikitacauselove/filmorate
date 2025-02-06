package com.example.application.repository;

import com.example.application.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для взаимодействия с действиями пользователей.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Получение списка всех действий пользователя.
     *
     * @param userId идентификатор пользователя
     */
    List<Event> findAllByUserId(Long userId);
}
