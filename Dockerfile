FROM        adoptopenjdk/openjdk11:alpine-jre
EXPOSE      8080

LABEL       maintainer="dreambash@mail.com"

ARG         JAR_FILE=target/*.jar

COPY        ${JAR_FILE} app.jar

ENTRYPOINT  ["java","-jar","app.jar"]
