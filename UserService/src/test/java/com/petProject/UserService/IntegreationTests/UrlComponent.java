package com.petProject.UserService.IntegreationTests;

import org.springframework.stereotype.Component;

/**
 * Компонент для получения url
 */
@Component
public class UrlComponent {

  //@LocalServerPort
  private int port;

  public String getBaseUrl(int port) {
    return String.format("http://localhost:%d/api/v1", port);
  }
}
