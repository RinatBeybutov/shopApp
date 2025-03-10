package com.petProject.UserService.repository;

import com.petProject.UserService.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с пользователями
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
