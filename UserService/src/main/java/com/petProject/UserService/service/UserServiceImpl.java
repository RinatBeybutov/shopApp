package com.petProject.UserService.service;

import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserViewDto;
import com.petProject.UserService.entity.UserEntity;
import com.petProject.UserService.entity.UserEntity.RangEnum;
import com.petProject.UserService.mapper.UserMapper;
import com.petProject.UserService.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для работы с пользователями
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Transactional
  @Override
  public UserViewDto save(UserCreateDto userCreateDto) {
    UserEntity entity = userMapper.toEntity(userCreateDto);
    entity.setRegisteredAt(LocalDate.now());
    entity.setOrderCount(0);
    entity.setRang(RangEnum.NO_RANG);
    entity = userRepository.save(entity);
    return userMapper.toDto(entity);
  }

  @Transactional
  @Override
  public List<UserViewDto> getUsers() {
    return userRepository.findAll()
        .stream()
        .map(userMapper::toDto)
        .toList();
  }

  @Transactional
  @Override
  public UserViewDto getUserById(UUID id) {
    UserEntity entity = userRepository.getByUuid(id);
    return userMapper.toDto(entity);
  }

  @Transactional
  @Override
  public void increaseOrderCount(UUID userUuid) {
    UserEntity entity = userRepository.getByUuid(userUuid);
    Integer orderCount = entity.getOrderCount();
    entity.setOrderCount(++orderCount);
    RangEnum currentRang = entity.getRang();
    if (currentRang != RangEnum.MASTER && orderCount > currentRang.getOrderLimit()) {
      RangEnum newRang = RangEnum.getNextRang(currentRang);
      entity.setRang(newRang);
    }
    userRepository.save(entity);
  }

  @Transactional
  @Override
  public void delete(UUID uuid) {
    UserEntity entity = userRepository.getByUuid(uuid);
    userRepository.delete(entity);
  }
}
