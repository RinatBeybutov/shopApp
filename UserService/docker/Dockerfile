FROM maven:3.8.4-openjdk-17

WORKDIR /app
COPY ./UserService.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar","UserService.jar"]