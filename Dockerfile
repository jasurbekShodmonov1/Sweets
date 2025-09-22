
FROM openjdk:21-jdk-slim


WORKDIR /app


COPY build/libs/sweets-1.0.0-plain.jar app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "app.jar"]
