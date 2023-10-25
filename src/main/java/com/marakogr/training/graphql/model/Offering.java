package com.marakogr.training.graphql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "offerings")
public class Offering {
    @Id
    private String id;

    private String name;

    private List<String> stockIds;

    public Offering() {
    }

    public Offering(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Offering(String id, String name, List<String> stockIds) {
        this.id = id;
        this.name = name;
        this.stockIds = stockIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStockIds() {
        return stockIds;
    }

    public void setStockIds(List<String> stockIds) {
        this.stockIds = stockIds;
    }

    @Override
    public String toString() {
        return "Offering{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", stockIds=" + stockIds +
            '}';
    }
}
