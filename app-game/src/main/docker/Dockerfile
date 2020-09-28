FROM openjdk:8-jre-alpine
ENV JAVA_OPTS=""
COPY target/*-application.jar application.jar
COPY src/main/resources/config/public.pem public.pem
ENTRYPOINT java ${JAVA_OPTS} -jar /application.jar