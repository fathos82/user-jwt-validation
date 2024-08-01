## Fase de Build
FROM ubuntu:latest AS build
LABEL authors="Athos"
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app
#COPY pom.xml .
#COPY src ./src
COPY . .

RUN mvn clean install -DskipTests


# Fase de Execução
FROM openjdk:21-jdk-slim
EXPOSE 8080

ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

#RUN mvn clean install -DskipTests
