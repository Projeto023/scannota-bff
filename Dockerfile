# First stage: build the application
FROM maven:3.8.5-openjdk-11-slim AS build
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the project
COPY src ./src
RUN mvn package -DskipTests

# Second stage: create the runtime image
FROM eclipse-temurin:11-jdk-alpine
WORKDIR /app

# Copy the jar file from the first stage
COPY --from=build /app/target/*.jar app.jar

# Set the entry point to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
