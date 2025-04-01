FROM maven:3.9.9-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
RUN mkdir -p /app/logs/archived
COPY --from=build /app/target/service-orders-*.jar app.jar

VOLUME /app/logs

ENTRYPOINT ["java", "-jar", "app.jar"]
