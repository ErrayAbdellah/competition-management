FROM  maven:3.9.6-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY ./pom.xml ./pom.xml

COPY ./src ./src

RUN mvn clean package  -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /target/competition-management-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
#FROM eclipse-temurin:17-jdk-alpine
#COPY target/*.jar app.jar
#EXPOSE 8081
#ENTRYPOINT ["java","-jar","/app.jar"]
