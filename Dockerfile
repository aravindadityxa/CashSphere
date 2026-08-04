# Build stage for backend
FROM maven:3.9.8-eclipse-temurin-21 AS backend-builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Build stage for frontend
FROM node:20-alpine AS frontend-builder
WORKDIR /app/frontend
COPY frontend/package.json frontend/package-lock.json ./
RUN npm ci
COPY frontend .
RUN npm run build

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy backend jar
COPY --from=backend-builder /app/target/*.jar app.jar

# Copy frontend dist
COPY --from=frontend-builder /app/frontend/dist ./src/main/resources/static

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
