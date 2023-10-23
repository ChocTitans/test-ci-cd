FROM openjdk:17-jdk
WORKDIR /work/

COPY target/*.jar /work/jdbc-1.0-SNAPSHOT.jar

EXPOSE 8082
CMD ["java","-jar","jdbc-1.0-SNAPSHOT.jar"]