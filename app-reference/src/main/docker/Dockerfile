FROM openjdk:8-jre-alpine
ENV JAVA_OPTS=""
COPY target/*-application.jar application.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /application.jar