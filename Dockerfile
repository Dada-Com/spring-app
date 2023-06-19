FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/My-portfolio-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080