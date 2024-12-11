# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY . .
RUN mvn clean package -DskipTests

EXPOSE 5050
ENTRYPOINT ["mvn", "tomcat7:run", "-Dmaven.tomcat.port=5050"]