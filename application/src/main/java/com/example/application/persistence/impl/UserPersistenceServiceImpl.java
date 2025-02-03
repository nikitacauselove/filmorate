package com.example.application.persistence.impl;

import com.example.application.domain.User;
import com.example.application.exception.NotFoundException;
import com.example.application.persistence.UserPersistenceService;
import com.example.application.persistence.mapper.UserEntityMapper;
import com.example.application.persistence.model.UserEntity;
import com.example.application.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserPersistenceServiceImpl implements UserPersistenceService {

    private final UserEntityMapper userEntityMapper;
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);

        return userEntityMapper.toDomain(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public User update(User user) {
        UserEntity userEntity = userRepository.findById(user.id())
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));

        return userEntityMapper.toDomain(userEntityMapper.updateEntity(user, userEntity));
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));

        return userEntityMapper.toDomain(userEntity);
    }

    @Override
    public List<User> findAll() {
        return userEntityMapper.toDomain(userRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addFriend(Long id, Long friendId) {
        UserEntity userEntity = userRepository.findByIdWithFriends(id)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));
        UserEntity friendEntity = userRepository.findById(friendId)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));

        userEntity.getFriends().add(friendEntity);
    }

    @Override
    @Transactional
    public void deleteFriend(Long id, Long friendId) {
        UserEntity userEntity = userRepository.findByIdWithFriends(id)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));
        UserEntity friendEntity = userRepository.findById(friendId)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));

        userEntity.getFriends().remove(friendEntity);
    }

    @Override
    public List<User> findFriends(Long id) {
        UserEntity userEntity = userRepository.findByIdWithFriends(id)
                .orElseThrow(() -> new NotFoundException(UserRepository.NOT_FOUND));

        return userEntityMapper.toDomain(userEntity.getFriends().stream()
                .toList());
    }

    @Override
    public List<User> findCommonFriends(Long id, Long otherUserId) {
        return userEntityMapper.toDomain(userRepository.findCommonFriends(id, otherUserId));
    }
}
