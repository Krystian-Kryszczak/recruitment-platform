FROM eclipse-temurin:21-alpine

COPY ./layers/ .
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "recruitment-platform.jar"]
