FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV DB_USER=postgres
ENV DB_PASSWORD=postgres
CMD ["java", "-Dspring.datasource.username=${DB_USER}", "-Dspring.datasource.password=${DB_PASSWORD}", "-jar", "/app.jar"]