package com.marakogr.training.graphql;

import com.marakogr.training.graphql.model.Stock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.util.List;

@Configuration
public class SubscriptionConfig {
    @Bean
    public Sinks.Many<List<Stock>> stockSink() {
        return Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);
    }

    @Bean
    public Flux<List<Stock>> stockFlux(Sinks.Many<List<Stock>> stockSink) {
        return stockSink.asFlux();
    }

}
