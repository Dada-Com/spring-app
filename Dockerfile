FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar My-portfolio-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java" "-jar" "/My-portfolio-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082