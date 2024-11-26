FROM maven:3.8.4-openjdk-17

WORKDIR /app
COPY ./UserService-0.0.1-SNAPSHOT.jar ./UserService.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","UserService.jar"]