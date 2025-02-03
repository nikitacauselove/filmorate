package com.example.application.service.impl;

import com.example.application.domain.Event;
import com.example.application.domain.EventType;
import com.example.application.domain.Operation;
import com.example.application.domain.User;
import com.example.application.persistence.EventPersistenceService;
import com.example.application.persistence.FilmPersistenceService;
import com.example.application.persistence.UserPersistenceService;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final EventPersistenceService eventPersistenceService;
    private final FilmPersistenceService filmPersistenceService;
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
    @Transactional
    public void deleteById(Long id) {
        filmPersistenceService.decreaseLikesAmount(id);
        userPersistenceService.deleteById(id);
    }

    @Override
    @Transactional
    public void addFriend(Long id, Long friendId) {
        User user = userPersistenceService.findById(id);

        userPersistenceService.addFriend(id, friendId);
        eventPersistenceService.create(Event.builder()
                .user(user)
                .eventType(EventType.FRIEND)
                .operation(Operation.ADD)
                .entityId(friendId)
                .build());
    }

    @Override
    @Transactional
    public void deleteFriend(Long id, Long friendId) {
        User user = userPersistenceService.findById(id);

        userPersistenceService.deleteFriend(id, friendId);
        eventPersistenceService.create(Event.builder()
                .user(user)
                .eventType(EventType.FRIEND)
                .operation(Operation.REMOVE)
                .entityId(friendId)
                .build());
    }

    @Override
    public List<User> findFriends(Long id) {
        return userPersistenceService.findFriends(id);
    }

    @Override
    public List<User> findCommonFriends(Long id, Long otherUserId) {
        return userPersistenceService.findCommonFriends(id, otherUserId);
    }
}
