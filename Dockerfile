# Build Stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy wrapper and pom first
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Grant execution permission and download dependencies
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Copy source code and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runtime Stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENV PORT=8080
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
