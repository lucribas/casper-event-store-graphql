# casper-event-store-graphql
An Event Store Implementation using Casper SDK +  MongoDB + GraphQL

# Technical Documentation
## Architecture
```plantuml
@startuml
'!theme sandstone
!theme plain
component casper as "casper node"
rectangle "Event Store" {
component back as "java service"
component db as "mongoDB"
}
actor User
back <-> casper
back <-l-> db
User <--> back : "GraphQL"
@enduml
```

```plantuml
@startwbs
* Event Store
** MongoDB
** Java Service
*** Casper Java SDK
*** Spring Boot
*** Spring Data MongoDB
*** GraphQL - ProjectReactor
** Tools
*** MongoDB Compass Gui
*** Postman


@endwbs
```


# 2. Requisites

## 2.1 MongoDB
Start MongoDB in container container:
```bash
cd docker
docker-compose up -d
```

Test your mongo instance:
```bash
docker exec -it mongo bash
mongo mongodb://admin:admin123@localhost:27017
show dbs
```

![MongoDB](docs/images/Screenshot%20from%202021-10-04%2016-27-12.png)



## 2.2 MongoDB Compass Gui

### 2.2.1 Install
**Install** MongoDB Compass in your localhost:
```bash
wget https://downloads.mongodb.com/compass/mongodb-compass_1.26.1_amd64.deb
sudo dpkg -i mongodb-compass_1.26.1_amd64.deb
```
source: https://docs.mongodb.com/compass/current/install/

### 2.2.2 Start

1. **Start** MongoDB Compass in your localhost:

```bash
mongodb-compass
```
![MongoDB Compass](docs/images/Screenshot%20from%202021-10-04%2016-24-22.png)

2. **Conect** to your MondoDB with the following URI:
`mongodb://admin:admin123@localhost:27017`







# 3. Future Improvements


## 3.1 Migrate to a Reactive Stack
### 3.1.1 Spring Data Reactive MongoDB (NOSQL)
(1)
**Title**: Use "Spring Data Reactive MongoDB" instead "Spring Data MongoDB"
**Justification**: Reactive apps allow you to scale better if you're dealing with lots of streaming data. They're non-blocking and tend to be more efficient because they're not tying up processing while waiting for stuff to happen. Reactive systems embrace asynchronous I/O.

(2)
**Title**: Use "Spring Reactive Web" instead "Spring Web"
**Justification**: The spring-web-reactive module contains the Spring Web Reactive framework that supports the @Controller programming model. It re-defines many of the Spring MVC contracts such as HandlerMapping and HandlerAdapter to be asynchronous and non-blocking and to operate on the reactive HTTP request and response.

### 3.1.2 Testing

(3)
#### Embedded MongoDB Database (TESTING)
**Title**: Use "Embedded MongoDB Database"
**Justification**: Provides a plataform neutral way for running MongoDB in unit tests.



test

<script id="asciicast-bpUwklc2PS45j4ifL2AWp6rm9" src="https://asciinema.org/a/bpUwklc2PS45j4ifL2AWp6rm9.js" async></script>

