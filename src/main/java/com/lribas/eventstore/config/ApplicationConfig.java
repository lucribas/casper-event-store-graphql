package com.lribas.eventstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Configuration
public class ApplicationConfig {
    @Bean
    public GraphQLScalarType jsonType() {
        return ExtendedScalars.Json;
    }

	@Bean
    public GraphQLScalarType longType() {
        return ExtendedScalars.GraphQLLong;
    }

}