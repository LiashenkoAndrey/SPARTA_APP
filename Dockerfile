FROM openjdk:17-jdk-alpine
COPY build/libs/*.jar /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]