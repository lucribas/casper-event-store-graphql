# casper-event-store-graphql
This repository contains the project submitted to Casper Hackathon: [The Event Store Implementation](https://gitcoin.co/issue/casper-network/gitcoin-hackathon/27/100026600)


### Challenge Description:
Build an event store implementation using an SDK + MongoDB + GraphQL.



## Technical Documentation

![source: docs/images/Diagrams_plant_uml.md](docs/images/Diagrams_plant_uml.png)


# Start Casper Network and MongoDB Containers

To improve my productivity I created a **docker-compose** file that runs two containers: one running a **casper network** using the nctl tool and other a **MongoDB** database.

So the first step is to enter the docker folder and run docker-compose as below.

```bash

cd docker/
docker-compose up
```

In another terminal we will start the casper network with the following commands:

```bash
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

Expected result:
![Casper Network](docs/images/Screenshot%20from%202021-10-10%2022-47-32.png)





### Testing MongoDB
Let's test if MongoDB is running correctly, open your MongoDB container:

```bash
docker exec -it mongo bash
mongo mongodb://admin:admin123@localhost:27017
show dbs
```
Expected result:
![MongoDB](docs/images/Screenshot%20from%202021-10-04%2016-27-12.png)


### Testing Casper Network

Let's test if Casper Network **event stream server** is accessible in your localhost.

```bash
curl -s  http://localhost:18102/events/main
```
Expected result:
![MongoDB](docs/images/Screenshot%20from%202021-10-10%2023-06-23.png)



# Start the Event Store

Run the following commands to use Maven to build and run the application:

```bash
mvn clean install
mvn spring-boot:run
```

In a few seconds the result of the processing of events from the Casper Network is expected to appear:
![evt](docs/images/Screenshot%20from%202021-10-10%2023-14-27.png)


## Execute some GraphQL requests

### Using GraphQLi
Open your browser on the following address:

[http://localhost:8080/graphiql](http://localhost:8080/graphiql?query=%7B%0A%20%20allBlocks%20%7B%0A%20%20%20%20hash%0A%20%20%20%20header%7B%0A%20%20%20%20%20%20parent_hash%0A%20%20%20%20%20%20state_root_hash%0A%20%20%20%20%20%20era_end%0A%20%20%20%20%20%20era_id%0A%20%20%20%20%7D%0A%20%20%20%20body%7B%0A%20%20%20%20%20%20proposer%0A%20%20%20%20%20%20deploy_hashes%0A%20%20%20%20%20%20transfer_hashes%0A%20%20%20%20%7D%0A%20%20%20%20proofs%0A%20%20%20%20createdAt%0A%20%20%7D%0A%7D%0A)
![evt](docs/images/Screenshot%20from%202021-10-10%2023-28-59.png)



### Using Curl

```bash
curl -g -X POST -H "Content-Type: application/json" -d '{"query":"query{allBlocks{hash}}"}' http://localhost:8080/graphql | jq
```
![evt](docs/images/Screenshot%20from%202021-10-10%2023-22-25.png)

```bash
curl -g -X POST -H "Content-Type: application/json" -d '{"query":"query{allBlocks {hash header{ parent_hash state_root_hash era_id } body{ proposer deploy_hashes transfer_hashes}proofs createdAt}}"}' http://localhost:8080/graphql | jq
```
![evt](docs/images/Screenshot%20from%202021-10-10%2023-21-37.png)





### Throubleshooting


Check if Casper Network started event stream server:

```bash
docker exec -it nctl /bin/bash
grep -R --include=*.log "started event stream server" ~/dev/casper-node/utils/nctl
```

Check if Casper Network is running properly:

```bash
docker exec -it nctl /bin/bash

lsof | grep LISTEN | cut -d':' -f2 | sort | uniq
```


# 2. Other Tools

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
2. **Conect** to your MondoDB with the following URI:
`mongodb://admin:admin123@localhost:27017`

![MongoDB Compass](docs/images/Screenshot%20from%202021-10-10%2023-33-25.png)
![MongoDB Compass](docs/images/Screenshot%20from%202021-10-10%2023-33-53.png)

