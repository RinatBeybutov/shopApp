package com.petProject.UserService.service;

import com.petProject.UserService.dto.UserCreateDto;
import com.petProject.UserService.dto.UserViewDto;
import com.petProject.UserService.entity.RangEnum;
import com.petProject.UserService.entity.UserEntity;
import com.petProject.UserService.mapper.UserMapper;
import com.petProject.UserService.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для работы с пользователями
 *
 * @author Rinat B
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final KeycloakService keycloakService;

  @Transactional
  @Override
  public UserViewDto create(UserCreateDto userCreateDto) {
    UserEntity entity = userMapper.toEntity(userCreateDto);
    entity.setRegisteredAt(LocalDate.now());
    entity.setOrderCount(0);
    entity.setRang(RangEnum.NO_RANG);
    entity = userRepository.save(entity);

    keycloakService.save(userCreateDto, entity.getUuid());

    return userMapper.toDto(entity);
  }

  @Transactional
  @Override
  public UserViewDto update(UUID userUuid, UserCreateDto updateDto) {
    UserEntity entity = userRepository.getByUuid(userUuid);
    userMapper.update(entity, updateDto);
    entity = userRepository.save(entity);
    return userMapper.toDto(entity);
  }

  @Transactional
  @Override
  public List<UserViewDto> getList() {
    return userRepository.findAll()
        .stream()
        .map(userMapper::toDto)
        .toList();
  }

  @Transactional
  @Override
  public UserViewDto getOne(UUID uuid) {
    UserEntity entity = userRepository.getByUuid(uuid);
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
    keycloakService.delete(uuid);
  }
}
