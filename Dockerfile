FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/dr4at-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]