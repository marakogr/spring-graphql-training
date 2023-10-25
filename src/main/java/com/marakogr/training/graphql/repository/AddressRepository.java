package com.marakogr.training.graphql.repository;

import com.marakogr.training.graphql.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
