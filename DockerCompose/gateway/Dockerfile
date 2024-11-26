FROM openjdk:17-jdk-alpine

RUN apk add --no-cache curl

WORKDIR /app
COPY ./Gateway-0.0.1-SNAPSHOT.jar ./Gateway.jar

EXPOSE 80
EXPOSE 8081
EXPOSE 8082

ENTRYPOINT ["java","-jar","Gateway.jar"]