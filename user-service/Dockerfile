FROM openjdk:17-jdk-alpine

LABEL authors="LaaLkA"

WORKDIR /app

ARG JAR_FILE=target/user-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]