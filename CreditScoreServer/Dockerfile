FROM openjdk:8

EXPOSE 8082

ADD target/CreditScoreServer-0.0.1-SNAPSHOT.jar creditServer.jar

ENTRYPOINT ["java","-jar","/creditServer.jar"]