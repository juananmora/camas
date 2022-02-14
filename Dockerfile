FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/*.jar camas-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS="
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /camas-0.0.1-SNAPSHOT.jar" ]