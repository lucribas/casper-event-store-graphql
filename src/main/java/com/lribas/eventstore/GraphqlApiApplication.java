package com.lribas.eventstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class GraphqlApiApplication {
    private static Logger logger = LoggerFactory.getLogger(GraphqlApiApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(GraphqlApiApplication.class, args);
        logger.info("GraphQL Running!!!");
    }
}
