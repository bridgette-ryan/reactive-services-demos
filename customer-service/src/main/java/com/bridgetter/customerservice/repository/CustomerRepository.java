package com.bridgetter.customerservice.repository;

import com.bridgetter.customerservice.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {

}
