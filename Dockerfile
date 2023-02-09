FROM openjdk:18.0.2.1-slim
VOLUME /tmp
ADD target/*.jar camas-0.0.1-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT [ "sh", "-c", "java -jar /camas-0.0.1-SNAPSHOT.jar" ]
