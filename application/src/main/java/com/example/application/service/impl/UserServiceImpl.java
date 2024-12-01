package com.example.application.service.impl;

import com.example.api.dto.UserDto;
import com.example.api.dto.enums.EventType;
import com.example.api.dto.enums.Operation;
import com.example.application.mapper.UserMapper;
import com.example.application.repository.entity.Film;
import com.example.application.repository.entity.User;
import com.example.application.repository.FilmRepository;
import com.example.application.repository.UserRepository;
import com.example.application.service.EventService;
import com.example.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final EventService eventService;
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

        return userMapper.updateUser(userDto, user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден"));
    }

    @Override
    public User findByIdWithFriends(Long id) {
        return userRepository.findByIdWithFriends(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с указанным идентификатором не найден"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long userId) {
        filmRepository.decreaseLikesAmount(userId);
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void addFriend(Long id, Long friendId) {
        User user = findByIdWithFriends(id);
        User friend = findById(friendId);

        user.getFriends().add(friend);
        eventService.create(id, EventType.FRIEND, Operation.ADD, friendId);
    }

    @Override
    @Transactional
    public void deleteFriend(Long id, Long friendId) {
        User user = findByIdWithFriends(id);
        User friend = findById(friendId);

        user.getFriends().remove(friend);
        eventService.create(id, EventType.FRIEND, Operation.REMOVE, friendId);
    }

    @Override
    public List<User> findAllFriends(Long id) {
        return findByIdWithFriends(id).getFriends().stream()
                .toList();
    }

    @Override
    public List<User> findCommonFriends(Long id, Long otherUserId) {
        return userRepository.findCommonFriends(id, otherUserId);
    }

    @Override
    public List<Film> findRecommendations(Long id) {
        List<Long> ids = userRepository.findAllForRecommendations(id);

        return filmRepository.findRecommendations(ids, id);
    }
}
