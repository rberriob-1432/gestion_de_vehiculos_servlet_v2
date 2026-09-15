FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM tomcat:10.1-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/gestion-vehiculos-mvc.war /usr/local/tomcat/webapps/gestion-vehiculos-mvc.war

EXPOSE 8080

CMD ["catalina.sh", "run"]