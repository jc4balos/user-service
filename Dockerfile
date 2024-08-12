FROM amazoncorretto:17
EXPOSE 8082
COPY target/*.jar /user_service.jar
COPY .env /
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "user_service.jar"]


