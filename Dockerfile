FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/My-portfolio-0.0.1-SNAPSHOT.jar My-portfolio.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","My-portfolio.jar"]