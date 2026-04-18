# -------------------------
# Stage 1: Build the Spring Boot application
# -------------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first to cache dependencies
COPY pom.xml ./

# Download dependencies offline
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the Spring Boot JAR (skip tests for faster builds)
RUN mvn clean package -DskipTests

# -------------------------
# Stage 2: Run the application
# -------------------------
FROM amazoncorretto:17

WORKDIR /app

EXPOSE 8082

COPY --from=build /app/target/*.jar ./user_service.jar

# Run with .env
ENTRYPOINT ["java", "-jar", "user_service.jar"]