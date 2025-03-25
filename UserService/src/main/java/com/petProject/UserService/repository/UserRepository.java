package com.petProject.UserService.repository;

import com.petProject.UserService.LocalizedException;
import com.petProject.UserService.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с пользователями
 *
 * @author Rinat B
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  Optional<UserEntity> findByUuid(UUID uuid);

  default UserEntity getByUuid(UUID uuid) {
    return findByUuid(uuid)
        .orElseThrow(() -> new LocalizedException("error.user.not_found", uuid));
  }
}
