# Build stage for backend
FROM maven:3.9.8-eclipse-temurin-21 AS backend-builder
WORKDIR /build/backend
COPY pom.xml .
RUN mvn dependency:go-offline -DskipTests
COPY src ./src
RUN mvn clean package -DskipTests -q

# Build stage for frontend
FROM node:20-alpine AS frontend-builder
WORKDIR /build/frontend
COPY frontend/package*.json ./
RUN npm ci --prefer-offline --no-audit
COPY frontend .
RUN npm run build

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy backend jar
COPY --from=backend-builder /build/backend/target/cashsphere-*.jar app.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:8080/api/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
