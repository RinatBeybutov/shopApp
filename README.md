# Введение

Этот проект создан для изучения микросервисной архитектуры, docker, k8s и СI/CD.
ShopApplication - это бэкенд для пиццерии, в дальнейшем планируется написание простенького фронта.

# Архитектура

![(скрин)](https://github.com/RinatBeybutov/shopApp/blob/master/arhitecture.jpg)

## Gateway 

Этот сервис представляет собой шлюз, который перенаправляет запросы на нужные сервисы.
Шлюз проверяет токен авторизации, который выдает keycloak.

## UserService

Предоставляет возможность выполнения CRUD операций с пользователями

### Скрипт для запуска проверки стиля кода в сервисе метрик

``` bash
mvn -P check-code-style validate
cd .\UserService\
```

### UI для swagger

http://localhost:8083/swagger-ui/index.html

## OrderService

Предоставляет возможность выполнения CRUD операций с заказами, продуктами и категориями.
Публикация сообщения о новом заказе в kafka.

### UI для swagger

http://localhost:8082/swagger-ui/index.html

## MetricService

Сервис для работы с метриками:
- Получение из кафки сообщений о новых заказах
- Подведение статистики о количестве заказов каждый час
- Сохранение статистики за весь день в sql

Запуск вспомогательных контейнеров для локальной разработки:

``` bash
docker compose -f utility-app.yml up -d
```

### UI для swagger

http://localhost:8081/swagger-ui/index.html

# Список технологий и библиотек

* Spring Boot (Web, Data, Security)
* PostgreSql
* Docker
* Swagger
* Junit, Mockito, Testcontainers
* Redis
* GitHub Actions (CI)
* Kafka
* Lombok
* Mapstruct
* Keycloak
* Scheduling spring
* Internalization spring