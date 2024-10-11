package com.example.application.repository;

import com.example.application.repository.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для взаимодействия с действими пользователей.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Получение списка всех действий пользователя.
     * @param userId идентификатор пользователя
     */
    List<Event> findAllByUserId(Long userId);
}
