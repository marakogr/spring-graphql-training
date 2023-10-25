package com.marakogr.training.graphql.api;

import com.marakogr.training.graphql.model.Address;
import com.marakogr.training.graphql.model.Stock;
import com.marakogr.training.graphql.repository.AddressRepository;
import com.marakogr.training.graphql.repository.StockRepository;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Controller
public class StockController {
    private final StockRepository stockRepository;
    private final AddressRepository addressRepository;
    private final Sinks.Many<List<Stock>> stockSink;
    private final Flux<List<Stock>> stockFlux;
    private final ConcurrentMap<String, FluxSink<List<Stock>>> subscribers = new ConcurrentHashMap<>();


    public StockController(StockRepository stockRepository,
                           AddressRepository addressRepository,
                           Sinks.Many<List<Stock>> stockSink,
                           Flux<List<Stock>> stockFlux) {
        this.stockRepository = stockRepository;
        this.addressRepository = addressRepository;
        this.stockSink = stockSink;
        this.stockFlux = stockFlux;
    }

    @MutationMapping
    public Stock addStock(final @Argument String offeringId,
                          final @Argument String addressId,
                          final @Argument Integer quantity) {
        Stock stock = new Stock(UUID.randomUUID().toString(), offeringId, addressId, quantity);
        this.stockRepository.save(stock);
        this.subscribers.get(offeringId).next(this.stockRepository.findByOfferingId(offeringId));
        this.stockSink.tryEmitNext(stockRepository.findAll());
        return stock;
    }

    @MutationMapping
    public Stock updateStock(final @Argument String stockId,
                             final @Argument Integer quantity) {
        Stock stock = this.stockRepository.findById(stockId)
            .orElseThrow(() -> new RuntimeException(String.format("Stock with id %s is not found", stockId)));
        stock.setQuantity(quantity);
        this.stockRepository.save(stock);
        String offeringId = stock.getOfferingId();
        this.subscribers.get(offeringId).next(this.stockRepository.findByOfferingId(offeringId));
        this.stockSink.tryEmitNext(stockRepository.findAll());
        return stock;
    }

    @SubscriptionMapping
    public Publisher<List<Stock>> subscribeStocks() {
        return stockFlux;
    }

    @SubscriptionMapping
    public Publisher<List<Stock>> offeringStocks(final @Argument String offeringId) {
        return Flux.create(subscriber ->
                subscribers.put(offeringId, subscriber.onDispose(() -> subscribers.remove(offeringId, subscriber))),
            FluxSink.OverflowStrategy.LATEST);
    }

    @SchemaMapping
    public Address address(final Stock stock) {
        return this.addressRepository.findById(stock.getAddressId()).orElse(null);
    }
}
