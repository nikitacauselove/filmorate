package com.example.application.persistence.impl;

import com.example.application.domain.EventType;
import com.example.application.domain.Film;
import com.example.application.domain.Operation;
import com.example.application.domain.User;
import com.example.application.persistence.UserPersistenceService;
import com.example.application.persistence.mapper.FilmEntityMapper;
import com.example.application.persistence.mapper.UserEntityMapper;
import com.example.application.persistence.model.EventEntity;
import com.example.application.persistence.model.UserEntity;
import com.example.application.persistence.repository.EventRepository;
import com.example.application.persistence.repository.FilmRepository;
import com.example.application.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserPersistenceServiceImpl implements UserPersistenceService {

    private final EventRepository eventRepository;
    private final FilmEntityMapper filmEntityMapper;
    private final FilmRepository filmRepository;
    private final UserEntityMapper userEntityMapper;
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        UserEntity entity = userEntityMapper.toEntity(user);

        return userEntityMapper.toDomain(userRepository.save(entity));
    }

    @Override
    public User update(User user) {
        UserEntity userEntity = userRepository.findById(user.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserRepository.NOT_FOUND));
        UserEntity updatedEntity = userEntityMapper.updateEntity(user, userEntity);

        return userEntityMapper.toDomain(updatedEntity);
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserRepository.NOT_FOUND));

        return userEntityMapper.toDomain(userEntity);
    }

    @Override
    public List<User> findAll() {
        return userEntityMapper.toDomain(userRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        filmRepository.decreaseLikesAmount(id);
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public void addOrDeleteFriend(Long id, Long friendId, Operation operation) {
        UserEntity userEntity = userRepository.findByIdWithFriends(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserRepository.NOT_FOUND));
        UserEntity friendEntity = userRepository.findById(friendId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserRepository.NOT_FOUND));

        switch (operation) {
            case ADD -> userEntity.getFriends().add(friendEntity);
            case REMOVE -> userEntity.getFriends().remove(friendEntity);
        }
        eventRepository.save(EventEntity.builder()
                .userId(id)
                .eventType(EventType.FRIEND)
                .operation(operation)
                .entityId(friendId)
                .build());
    }

    @Override
    public List<User> findFriends(Long id) {
        List<UserEntity> userEntityList = userRepository.findByIdWithFriends(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserRepository.NOT_FOUND))
                .getFriends().stream()
                .toList();

        return userEntityMapper.toDomain(userEntityList);
    }

    @Override
    public List<User> findCommonFriends(Long id, Long otherUserId) {
        return userEntityMapper.toDomain(userRepository.findCommonFriends(id, otherUserId));
    }

    @Override
    public List<Film> findRecommendations(Long id) {
        List<Long> ids = userRepository.findRelevantUserIds(id);

        return filmEntityMapper.toDomain(filmRepository.findRecommendations(id, ids));
    }
}
