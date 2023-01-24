package com.bridgetter.customerservice.service;

import com.bridgetter.customerservice.model.dto.CustomerDto;
import com.bridgetter.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    @Value("${config.hosts.order-service}")
    private String orderServiceHost ;

    private final CustomerRepository customerRepository ;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Flux<CustomerDto> getAllCustomers() {
        return customerRepository
                .findAll()
                .map(CustomerDto::new);
    }

    public Mono<CustomerDto> getCustomerById(String id) {
        return customerRepository
                .findById(Integer.valueOf(id))
                .map(CustomerDto::new) ;
    }


    public Flux<Object> getCustomerOrders(String id) {
        WebClient orderServiceClient = WebClient.create(orderServiceHost) ;
        return orderServiceClient.get()
                .uri("/customer-orders/{id}", id )
                .retrieve()
                .bodyToFlux(Object.class) ;
    }

    public Flux<Object> getCustomerOrderLines(String id) {
        WebClient orderServiceClient = WebClient.create(orderServiceHost) ;
        return orderServiceClient.get()
                .uri("/order-lines/{id}", id )
                .retrieve()
                .bodyToFlux(Object.class) ;
    }


}
