FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" ,"-Djava.security.egd=file:/dev/./urandom","-jar", "application.jar"]
#FROM postgres:13.1-alpine
#ADD ../resources/data.sql /docker-entrypoint-initdb.d/