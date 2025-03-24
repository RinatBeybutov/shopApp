package com.petProject.UserService.config;

/**
 * API для работы с пользователями.
 */
public class API {

  private API() {
  }

  private static final String prefix = "/api/v1";

  public static final String USER_API = prefix + "/users";

  public static final String RANGS_API = prefix + "/rangs";
}
