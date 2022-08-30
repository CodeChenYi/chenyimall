FROM openjdk:8-jdk-slim
LABEL maintainer=chenyi

EXPOSE 8001

COPY target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "-Xms200m", "-Xmx200m", "-Xmn200m", "/app.jar"]