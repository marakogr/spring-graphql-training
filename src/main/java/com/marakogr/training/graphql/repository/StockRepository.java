package com.marakogr.training.graphql.repository;

import com.marakogr.training.graphql.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockRepository extends MongoRepository<Stock, String> {
    List<Stock> findByOfferingId(String offeringId);
}
