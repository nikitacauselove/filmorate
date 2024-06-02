package com.example.backend.service.impl;

import com.example.backend.entity.Film;
import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

//    private final EventDao eventDao;
//    private final FilmDao filmDao;
    private final UserRepository userRepository;

    @Override
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
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден."));
    }

    @Override
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

//      eventDao.create(new Event(null, null, id, Event.EventType.FRIEND, Event.Operation.ADD, friendId));
    }

    @Override
    @Transactional
    public void deleteFriend(Long id, Long friendId) {
        User user = findById(id);
        User friend = findById(friendId);

        user.getFriends().remove(friend);

//      eventDao.create(new Event(null, null, id, Event.EventType.FRIEND, Event.Operation.REMOVE, friendId));
    }

    @Override
    @Transactional
    public List<User> findAllFriends(Long id) {
        User user = findById(id);

        System.out.println(user.getFriends());
        return user.getFriends();
    }

    @Override
    public List<User> findCommonFriends(Long id, Long otherUserId) {
        return userRepository.findCommonFriends(id, otherUserId);
    }

    @Override
    public List<Film> findRecommendations(Long id) {
//        return filmDao.findRecommendations(id);
        return null;
    }
}
