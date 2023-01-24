package com.bridgetter.orderservice.repository;

import com.bridgetter.orderservice.entity.Order;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {

    Flux<Order> findByCustomerId(Integer customerId);

}
