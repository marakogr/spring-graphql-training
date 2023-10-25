FROM openjdk:17-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY target/*.jar app/application.jar
ADD /src/main/resources/application.yml  app/application.yml
ENTRYPOINT exec java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar app/application.jar --spring.config.location=/app/
EXPOSE 8080
EXPOSE 5005