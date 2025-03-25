package com.petProject.UserService;

import lombok.Getter;

/**
 * Исключение, которое используется для локализованных сообщений об ошибках.
 *
 * @author Rinat B
 */
@Getter
public class LocalizedException extends RuntimeException {

  private final Object[] args;

  public LocalizedException(String message, Object... args) {
    super(message);
    this.args = args;
  }
}
