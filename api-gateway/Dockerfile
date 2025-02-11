FROM openjdk:17-jdk-alpine

LABEL authors="LaaLkA"

WORKDIR /app

ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]