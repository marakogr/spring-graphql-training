package com.marakogr.training.graphql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")
public class Stock {
    @Id
    private String id;
    private String offeringId;
    private String addressId;
    private Integer quantity;

    public Stock(String id, String offeringId, String addressId, Integer quantity) {
        this.id = id;
        this.offeringId = offeringId;
        this.addressId = addressId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(String offeringId) {
        this.offeringId = offeringId;
    }

    @Override
    public String toString() {
        return "Stock{" +
            "id='" + id + '\'' +
            ", offeringId='" + offeringId + '\'' +
            ", address='" + addressId + '\'' +
            ", quantity=" + quantity +
            '}';
    }
}
