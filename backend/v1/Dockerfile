# Use maven for building
FROM maven:3.8.5-openjdk-21-slim AS build

# Set the working directory inside the container
WORKDIR /koiratreffit/backend

# Copy pom.xml and source code
COPY pom.xml ./
COPY src ./src

# Build the application with maven
RUN mvn clean package

# create the runtime image
FROM openjdk:21-jdk-slim

# Copy the built JAR file
COPY --from=build /koiratreffit/backend/target/koiratreffit-backend.v1.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
