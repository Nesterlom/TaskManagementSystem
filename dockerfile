FROM openjdk:latest

ARG JAR_FILE=target/*.jar

WORKDIR /app

EXPOSE 8081

COPY ./target/TaskManagementSystem-0.0.1-SNAPSHOT.jar /app/TaskManagementSystem-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/app/TaskManagementSystem-0.0.1-SNAPSHOT.jar"]