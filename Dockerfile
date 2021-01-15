FROM openjdk:11-jre-slim
MAINTAINER "HRMS App <neoOkpara@neoOkpara.io>"
WORKDIR /app
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ./app.jar
CMD ["java", "-jar", "-Dspring.profiles.active=uat", "/app/app.jar"]

EXPOSE 8080