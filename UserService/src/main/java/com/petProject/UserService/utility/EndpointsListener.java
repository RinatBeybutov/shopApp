package com.petProject.UserService.utility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Компонент для отображения эндпоинтов
 *
 * @author Rinat B
 */
@Component
public class EndpointsListener implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    ApplicationContext applicationContext = event.getApplicationContext();
    applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods()
        .forEach((s, a) -> System.out.println(s));
  }
}
