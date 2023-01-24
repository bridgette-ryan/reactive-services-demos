package com.bridgetter.orderservice.repository;

import com.bridgetter.orderservice.entity.OrderLine;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.Optional;

@Repository
public interface OrderLineRepository extends ReactiveCrudRepository<OrderLine, Tuple2<Integer,String>> {

    Flux<OrderLine> findByTransactId(Integer transactId);
}
