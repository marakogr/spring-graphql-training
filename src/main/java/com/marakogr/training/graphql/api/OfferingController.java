package com.marakogr.training.graphql.api;

import com.marakogr.training.graphql.model.Offering;
import com.marakogr.training.graphql.model.Stock;
import com.marakogr.training.graphql.repository.OfferingRepository;
import com.marakogr.training.graphql.repository.StockRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Controller
public class OfferingController {
    private final StockRepository stockRepository;
    private final OfferingRepository offeringRepository;

    public OfferingController(StockRepository stockRepository,
                              OfferingRepository offeringRepository) {
        this.stockRepository = stockRepository;
        this.offeringRepository = offeringRepository;
    }

    @MutationMapping
    public Offering addOffering(final @Argument String name) {
        return this.offeringRepository.save(new Offering(UUID.randomUUID().toString(), name));
    }

    @QueryMapping
    //TODO: add paging, filtering by name
    public Collection<Offering> allOfferings() {
        return offeringRepository.findAll();
    }

    @SchemaMapping(field = "stocks")
    //TODO: add batch mapping
    public List<Stock> stocks(final Offering offering) {
        return stockRepository.findAllById(offering.getStockIds());
    }

}
