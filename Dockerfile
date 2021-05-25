FROM        adoptopenjdk/openjdk11:alpine-jre

LABEL       maintainer="dreambash@mail.com"

ARG         JAR_FILE=target/*.jar

COPY        ${JAR_FILE} app.jar

ENTRYPOINT  ["java","-jar","app.jar"]




#FROM adoptopenjdk/openjdk11:alpine-jre

#EXPOSE 8080

#ARG JAR_FILE=target/*.jar

#WORKDIR /myfolder

#RUN java --version

#COPY ${JAR_FILE} app.jar

#ENV MY_DATABASE_USERNAME=username
#ENV MY_DATABASE_PASSWORD=password123

#ENTRYPOINT ["java","-jar","app.jar"]


