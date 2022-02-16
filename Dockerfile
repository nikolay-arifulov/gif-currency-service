FROM gradle:7.4.0-jdk11 AS build
COPY --chown=gradle:gradle build.gradle settings.gradle /home/app/
COPY --chown=gradle:gradle src /home/app/src
WORKDIR /home/app
RUN gradle build --no-daemon

FROM openjdk:11-jre-slim
COPY --from=build /home/app/build/libs/*.jar /app/gif-currency-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/gif-currency-service.jar"]