package com.lribas.eventstore.repository;

import com.lribas.eventstore.model.BlockAdded;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlockRepository extends MongoRepository<BlockAdded, String> {

}
