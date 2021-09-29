FROM openjdk:8

EXPOSE 8083

ADD target/CustomerServer-0.0.1-SNAPSHOT.jar customerServer.jar

ENTRYPOINT ["java","-jar","/customerServer.jar"]