# Fase construcción
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Fase ejecución
FROM openjdk:21-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*

COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

COPY --from=build /app/target/*.jar marketplace.jar

EXPOSE 8081

CMD ["/app/wait-for-it.sh", "cloud-config", "8888", "java", "-jar", "marketplace.jar"]
