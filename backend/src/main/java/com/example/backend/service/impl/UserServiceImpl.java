package com.example.backend.service.impl;

import com.example.backend.entity.Event;
import com.example.backend.entity.Film;
import com.example.backend.entity.User;
import com.example.backend.repository.FilmRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.EventService;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final EventService eventService;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User create(User user) {
        userRepository.save(user);

        return findById(user.getId());
    }

    @Override
    @Transactional
    public User update(User user) {
        userRepository.save(user);

        return findById(user.getId());
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден."));
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void addFriend(Long id, Long friendId) {
        User user = findById(id);
        User friend = findById(friendId);

        user.getFriends().add(friend);
        eventService.create(new Event(null, LocalDateTime.now(), id, Event.EventType.FRIEND, Event.Operation.ADD, friendId));
    }

    @Override
    @Transactional
    public void deleteFriend(Long id, Long friendId) {
        User user = findById(id);
        User friend = findById(friendId);

        user.getFriends().remove(friend);
        eventService.create(new Event(null, LocalDateTime.now(), id, Event.EventType.FRIEND, Event.Operation.REMOVE, friendId));
    }

    @Override
    @Transactional
    public List<User> findAllFriends(Long id) {
        User user = findById(id);

        System.out.println(user.getFriends());
        return user.getFriends().stream().toList();
    }

    @Override
    @Transactional
    public List<User> findCommonFriends(Long id, Long otherUserId) {
        return userRepository.findCommonFriends(id, otherUserId);
    }

    @Override
    @Transactional
    public List<Film> findRecommendations(Long id) {
        List<Long> listOfUserId = userRepository.findAllForRecommendations(id);

        return filmRepository.findRecommendations(listOfUserId, id);
    }

    public static final String mostRelevantUsersSql = """
            SELECT user_id
            FROM (
                SELECT user_id, max(count)
                FROM (
                    SELECT user_id, count(film_id) AS count
                    FROM film_likes
                    WHERE film_id in (
                        SELECT film_id
                        FROM film_likes
                        WHERE user_id = :id
                    ) AND user_id <> :id
                    GROUP BY user_id
                )
                GROUP BY user_id
            )
            """;

    public static final String recommendedFilmsSql = """
            SELECT *
            FROM films
            WHERE id in (
                SELECT film_id
                FROM film_likes
                WHERE user_id in :listOfUserId
            
                EXCEPT
            
                SELECT film_id
                FROM film_likes
                WHERE user_id = :user_id
            )
            """;
}
