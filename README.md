# Gittigidiyor Java Spring Bootcamp Graduation Project

## Bank credit approval system

A REST API projects with Spring Boot

## Requirements

* [JDK 8+](https://www.oracle.com/java/technologies/javase-downloads.html)
* [Apache Maven](https://maven.apache.org/download.cgi) for terminal
* [Docker](https://www.docker.com/) for dockerized run

## Steps to Setup
**1. Clone the application.**

```bash
git clone https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-avemphract.git
```

**2. Navigate to the root folder of the project and run the following command.**

***Start services locally***

Windows:
* MongoDb and Mongo Express (for error logging)
* Optional with docker
```bash
cd CustomerServer
docker-compose -f docker-compose.yaml up -d
```
* Eureka Server
```bash
cd EurekaServer
mvn spring-boot:run
```
* Credit Score Server
```bash
cd CreditScoreServer
mvn spring-boot:run
```
* Customer Server
```bash
cd CustomerServer
mvn spring-boot:run
```
***Start services with docker***
* Firstly it has need built the project, for run with docker.
```bash
mvn clean install
docker-compose -f docker-compose.yaml up -d
```

The app uses
* PORT:8081 mongo-express   
* PORT:27017 mongodb         
* PORT:8761 eureka-server   
* PORT:8082 credit-score-server 
* PORT:8083 customer-server  

For the application to stand up in a healthy way, make sure these ports are not used.


## Endpoints

### Customer Server
#### Credit Approvals
* [Find All](doc/CustomerServer/credit-approvals/find-all.md) `GET http://localhost:8083/api/creditApprovals`
* [Find By Id](doc/CustomerServer/credit-approvals/find-by-id.md) `GET http://localhost:8083/api/creditApprovals/:id`
* [Find By Tc](doc/CustomerServer/credit-approvals/find-by-tc.md) `GET http://localhost:8083/api/creditApprovals/tc/:tc`
* [Save](doc/CustomerServer/credit-approvals/save.md) `POST http://localhost:8083/api/creditApprovals`
* [Update](doc/CustomerServer/credit-approvals/update.md) `PUT http://localhost:8083/api/creditApprovals`
* [Delete](doc/CustomerServer/credit-approvals/delete.md) `DELETE http://localhost:8083/api/creditApprovals`
#### Customers
* [Find All](doc/CustomerServer/customers/find-all.md) `GET http://localhost:8083/api/customers`
* [Find By Id](doc/CustomerServer/customers/find-by-id.md) `GET http://localhost:8083/api/customers/:id`
* [Find By Tc](doc/CustomerServer/customers/find-by-tc.md) `GET http://localhost:8083/api/customers/tc/:tc`
* [Credit Application](doc/CustomerServer/customers/credit-application.md) `POST http://localhost:8083/api/customers`
* [Save](doc/CustomerServer/customers/save.md) `POST http://localhost:8083/api/customers`
* [Update](doc/CustomerServer/customers/update.md) `PUT http://localhost:8083/api/customers`
* [Delete](doc/CustomerServer/customers/delete.md) `DELETE http://localhost:8083/api/customers`
#### Messages
* [Find All](doc/CustomerServer/messages/find-all.md) `GET http://localhost:8083/api/messages`
* [Find By Id](doc/CustomerServer/messages/find-all.md) `GET http://localhost:8083/api/messages/:id`
* [Save](doc/CustomerServer/messages/save.md) `POST http://localhost:8083/api/messages`
* [Update](doc/CustomerServer/messages/update.md) `PUT http://localhost:8083/api/messages`
* [Delete](doc/CustomerServer/messages/delete.md) `DELETE http://localhost:8083/api/messages`
#### Errors
* [Find All](doc/CustomerServer/errors/find-all.md) `GET http://localhost:8083/api/errors`
* [Find By Id](doc/CustomerServer/errors/find-by-id.md) `GET http://localhost:8083/api/errors/:id`
### Credit Score Server

* [Find All](doc/CreditScoreServer/credit-scores/find-all.md) `GET http://localhost:8082/api/creditScores`
* [Find By Id](doc/CreditScoreServer/credit-scores/find-by-id.md) `GET http://localhost:8082/api/creditScores/:id`
* [Find By Tc](doc/CreditScoreServer/credit-scores/find-by-tc.md) `GET http://localhost:8082/api/creditScores/tc/:tc`
* [Save](doc/CreditScoreServer/credit-scores/save.md) `POST http://localhost:8082/api/creditScores`
* [Update](doc/CreditScoreServer/credit-scores/update.md) `PUT http://localhost:8082/api/creditScores`
* [Delete](doc/CreditScoreServer/credit-scores/delete.md) `GET http://localhost:8082/api/creditScores`

## Other internal links
#### Customer Service other
* http://localhost:8083/swagger-ui.html#/ Swagger UI
* http://localhost:8083/h2-console
  ```bash
  url=jdbc:h2:mem:testdb
  driverClassName=org.h2.Driver
  username=sa
  password=
  ```

#### Credit Score Service other
* http://localhost:8082/swagger-ui.html#/ Swagger UI
* http://localhost8082/h2-console
  ```bash
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=
  ```

#### Eureka Server
* http://localhost:8761/ Spring Eureka
## Author
**Emre Çatalkaya**
[github/avemphract](https://github.com/avemphract)