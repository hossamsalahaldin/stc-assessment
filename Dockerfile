#FROM openjdk:19-jdk-alpine
#WORKDIR /stc-assessments
#COPY src ./src
#COPY pom.xml ./
#RUN mvn clean install
#CMD ["java","-jar", "./target/stc-assessments-1.0-SNAPSHOT.jar"]

FROM maven:3.9-sapmachine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

#
# Package stage
#
FROM openjdk:19-jdk-alpine
COPY --from=build /home/app/target/stc-task-demo-0.0.1-SNAPSHOT.jar /usr/local/lib/stc-task-demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/stc-task-demo-0.0.1-SNAPSHOT.jar"]