FROM eclipse-temurin:21-jre-alpine
LABEL authors="Thomas_Mylonas"

COPY target/petstore_api_app-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar" , "petstore_api_app-0.0.1-SNAPSHOT.jar"]
