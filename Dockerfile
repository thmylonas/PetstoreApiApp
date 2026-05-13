# Build stage
FROM  maven:3.9.15-amazoncorretto-21-alpine AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src
RUN mvn dependency:go-offline -B
RUN mvn -DskipTests clean package

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
LABEL authors="Thomas_Mylonas"

WORKDIR /app

COPY --from=build /build/target/*.jar /app

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=${ENV_ACTIVE_PROFILE}", "petstore_api_app-0.0.1-SNAPSHOT.jar"]
