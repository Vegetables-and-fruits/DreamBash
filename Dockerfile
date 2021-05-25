FROM        adoptopenjdk/openjdk11:alpine-jre
LABEL       maintainer="dreambash@mail.com"
VOLUME      /recipe-app
ARG         JAR_FILE=target/*.jar
COPY        ${JAR_FILE} app.jar
ENTRYPOINT  ["java","-jar","app.jar"]




