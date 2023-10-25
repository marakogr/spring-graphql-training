package com.marakogr.training.graphql.repository;

import com.marakogr.training.graphql.model.Offering;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OfferingRepository extends MongoRepository<Offering, String> {
}
