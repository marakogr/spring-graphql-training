package com.marakogr.training.graphql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
public class Address {
    @Id
    private String id;
    private String fullAddress;

    public Address(String id, String fullAddress) {
        this.id = id;
        this.fullAddress = fullAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
            "id='" + id + '\'' +
            ", address='" + fullAddress + '\'' +
            '}';
    }
}
