FROM maven:3.8.4-openjdk-17

WORKDIR /app
COPY ./OrderService-0.0.1-SNAPSHOT.jar ./OrderService.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","OrderService.jar"]