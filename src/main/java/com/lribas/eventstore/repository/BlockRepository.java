package com.lribas.eventstore.repository;

import com.lribas.eventstore.model.Block;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlockRepository extends MongoRepository<Block, String> {

}
