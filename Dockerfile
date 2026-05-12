# Build stage
FROM eclipse-temurin:21-jre-alpine AS build

WORKDIR /build

COPY .mvn .mvn/
COPY mvnw .
COPY pom.xml .
COPY src .
RUN chmod +x ./mvnw && ./mvnw -DskipTests clean package

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
LABEL authors="Thomas_Mylonas"

WORKDIR /app

COPY --from=build /build/target/*.jar /app

ENV ENV_POSTGRES_HOST=${ENV_POSTGRES_HOST}
ENV ENV_POSTGRES_PORT=${ENV_POSTGRES_PORT}
ENV ENV_POSTGRES_DB_NAME=${ENV_POSTGRES_DB_NAME}
ENV ENV_POSTGRES_USERNAME=${ENV_POSTGRES_USERNAME}
ENV ENV_POSTGRES_PASSWORD=${ENV_POSTGRES_PASSWORD}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "petstore_api_app-0.0.1-SNAPSHOT.jar"]
