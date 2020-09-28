FROM openjdk:8-jre-alpine
ENV JAVA_OPTS=""
COPY target/*-application.jar application.jar
COPY src/main/resources/config/public.pem public.pem
COPY src/main/resources/config/private.key private.key
ENTRYPOINT java ${JAVA_OPTS} -jar /application.jar