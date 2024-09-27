package com.petProject.UserService.service;

import com.petProject.UserService.dto.UserDto;
import com.petProject.UserService.entity.UserEntity;
import com.petProject.UserService.mapper.UserMapper;
import com.petProject.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto save(UserDto userDto) {
        UserEntity entity = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(entity));
    }

    @Transactional
    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public UserDto getUserById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Not Found user with id " + id));
    }
}
