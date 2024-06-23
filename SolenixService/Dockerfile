FROM openjdk:17
EXPOSE 8282
ADD target/solanix-service-docker.jar solenixservice.jar
ENTRYPOINT ["java", "-jar", "solenixservice.jar"]