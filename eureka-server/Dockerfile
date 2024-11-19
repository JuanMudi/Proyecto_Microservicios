# Fase construcción
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Fase ejecución
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar eureka-server.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "eureka-server.jar"]
