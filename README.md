


https://www.youtube.com/watch?v=bdfEjn6xZx0
https://github.com/graphql-java-kickstart/graphql-spring-boot/blob/master/graphql-spring-boot-test/src/test/java/com/graphql/spring/boot/test/GraphQLTestSubscriptionAwaitAndGetResponseTest.java


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


# RUN

```bash

cd docker/
docker-compose up

# other terminal

docker exec -it nctl /bin/bash

cd ~/dev
source ~/.bashrc
source env/bin/activate
source casper-node/utils/nctl/activate

nctl-assets-setup
sleep 4
figlet "Starting"
nctl-start
sleep 1
nctl-status
```

mvn clean install
mvn spring-boot:run















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


Use Hash as MongoDB identifier instead ObjectID auto-generated id.


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
<a href="https://asciinema.org/a/bpUwklc2PS45j4ifL2AWp6rm9" target="_blank"><img src="https://asciinema.org/a/bpUwklc2PS45j4ifL2AWp6rm9.svg" /></a>

time docker-compose build




docker run --interactive --tty --entrypoint /bin/sh docker_nctl
# remove chaincode docker images
docker rm $(docker ps -aq)
# remove volumes
docker volume rm $(docker volume ls -q)


docker system prune -a



docker run --interactive --tty --entrypoint /bin/bash docker_nctl
docker run --interactive --tty --entrypoint /bin/bash docker_casper-client



## START
cd ~/dev
/root/evt/docker/nctl/nctl_compile.bash

### tools
nctl-assets-setup
sleep 10
nctl-start
nctl-status

nctl-view-faucet-account

---------------------------------------------



casper-client get-state-root-hash --node-address http://localhost:11101

nctl-view-faucet-account

grep -R "started event stream server" ~/dev/casper-node/utils/nctl/dumps

curl -s  http://localhost:18102/events/main

lsof | grep LISTE | cut -d':' -f2 | sort | uniq
11101 (LISTEN)
11102 (LISTEN)
11103 (LISTEN)
11104 (LISTEN)
11105 (LISTEN)
14101 (LISTEN)
14102 (LISTEN)
14103 (LISTEN)
14104 (LISTEN)
14105 (LISTEN)
18101 (LISTEN)
18102 (LISTEN)
18103 (LISTEN)
18104 (LISTEN)
18105 (LISTEN)
22101 (LISTEN)
22102 (LISTEN)
22103 (LISTEN)
22104 (LISTEN)
22105 (LISTEN)










curl -g -X POST -H "Content-Type: application/json" -d '{"query":"query{allBlocks {hashId}}"}' http://localhost:8080/graphql | jq








