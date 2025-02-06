package com.example.application.service.impl;

import com.example.api.model.UserDto;
import com.example.application.entity.Event;
import com.example.application.entity.EventType;
import com.example.application.entity.Operation;
import com.example.application.entity.User;
import com.example.application.exception.NotFoundException;
import com.example.application.mapper.UserMapper;
import com.example.application.repository.EventRepository;
import com.example.application.repository.FilmRepository;
import com.example.application.repository.UserRepository;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final EventRepository eventRepository;
    private final FilmRepository filmRepository;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(UserDto userDto) {
        User user = findById(userDto.id());

        return userMapper.updateEntity(userDto, user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        filmRepository.decreaseLikesAmount(id);
    }

    @Override
    @Transactional
    public void addFriend(Long id, Long friendId) {
        User user = userRepository.findByIdWithFriends(id)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));
        User friend = findById(friendId);

        user.getFriends().add(friend);
        eventRepository.save(Event.builder()
                .userId(id)
                .eventType(EventType.FRIEND)
                .operation(Operation.ADD)
                .entityId(friendId)
                .build());
    }

    @Override
    @Transactional
    public void deleteFriend(Long id, Long friendId) {
        User user = userRepository.findByIdWithFriends(id)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));
        User friend = findById(friendId);

        user.getFriends().remove(friend);
        eventRepository.save(Event.builder()
                .userId(id)
                .eventType(EventType.FRIEND)
                .operation(Operation.REMOVE)
                .entityId(friendId)
                .build());
    }

    @Override
    public List<User> findFriends(Long id) {
        User user = userRepository.findByIdWithFriends(id)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));

        return user.getFriends().stream()
                .toList();
    }

    @Override
    public List<User> findCommonFriends(Long id, Long otherUserId) {
        return userRepository.findCommonFriends(id, otherUserId);
    }
}
