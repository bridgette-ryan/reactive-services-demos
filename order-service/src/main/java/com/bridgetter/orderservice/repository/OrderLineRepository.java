package com.bridgetter.orderservice.repository;

import com.bridgetter.orderservice.entity.OrderLine;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.Optional;

@Repository
public interface OrderLineRepository extends ReactiveCrudRepository<OrderLine, Tuple2<Integer,String>> {

    Flux<OrderLine> findByTransactId(Integer transactId);

    @Query("SELECT order_lines.* FROM order_lines " +
            "JOIN orders " +
            "ON order_lines.transact_id = orders.transact_id " +
            "WHERE " +
            "order_lines.transact_id = :transact_id " +
            "AND orders.customer_id = :customer_id")
    Flux<OrderLine> findByTransactIdTest(
            @Param("customer_id") Integer customerId,
            @Param("transact_id") Integer transactId);
}
