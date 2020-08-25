FROM maven:3.6.3-slim as build
WORKDIR /app
COPY ./ .
RUN  mvn package -Dmaven.test.skip=true

FROM openjdk:14-slim as runtime
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar
ENTRYPOINT ["java","-jar","app.jar"]
