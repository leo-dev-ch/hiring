FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
WORKDIR /tmp

COPY ./build/libs/hiring-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/tmp/api.jar"]