# Stage 1: Build the Spring Boot application
FROM gradle:jdk17 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build

# Stage 2: Create the final Docker image
FROM openjdk:17 as create_image

WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/waw-2023-fall-oleksandr-be.jar

EXPOSE 8080

CMD ["java", "-jar", "waw-2023-fall-oleksandr-be.jar"]
