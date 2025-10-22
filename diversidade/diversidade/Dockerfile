# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY diversidade/diversidade/pom.xml .
COPY diversidade/diversidade/src ./src
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Create a non-root user
RUN useradd -m -u 1001 appuser
USER appuser

EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"] 