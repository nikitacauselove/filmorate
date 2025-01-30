package com.example.application.service.impl;

import com.example.api.model.type.Operation;
import com.example.application.domain.Film;
import com.example.application.domain.User;
import com.example.application.persistence.UserPersistenceService;
import com.example.application.persistence.model.FilmEntity;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserPersistenceService userPersistenceService;

    @Override
    public User create(User user) {
        return userPersistenceService.create(user);
    }

    @Override
    public User update(User user) {
        return userPersistenceService.update(user);
    }

    @Override
    public User findById(Long id) {
        return userPersistenceService.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userPersistenceService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userPersistenceService.deleteById(id);
    }

    @Override
    @Transactional
    public void addOrDeleteFriend(Long id, Long friendId, Operation operation) {
        userPersistenceService.addOrDeleteFriend(id, friendId, operation);
    }

    @Override
    public List<User> findFriends(Long id) {
        return userPersistenceService.findFriends(id);
    }

    @Override
    public List<User> findCommonFriends(Long id, Long otherUserId) {
        return userPersistenceService.findCommonFriends(id, otherUserId);
    }

    @Override
    public List<Film> findRecommendations(Long id) {
        return userPersistenceService.findRecommendations(id);
    }
}
