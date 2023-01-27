package com.bridgetter.orderservice.service;

import com.bridgetter.orderservice.model.OrderDto;
import com.bridgetter.orderservice.model.OrderLineDto;
import com.bridgetter.orderservice.repository.OrderLineRepository;
import com.bridgetter.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OrderService {
    private final OrderRepository orderRepository ;
    private final OrderLineRepository orderLineRepository;

    public OrderService(OrderRepository orderRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    public Flux<OrderDto> getOrdersByCustomerId(String id) {
        return orderRepository.findByCustomerId(Integer.valueOf(id)).map(OrderDto::new) ;
    }


    public Flux<OrderLineDto> getOrderLinesByIdAndCustomerId(String customerId, String orderId) {
        Flux<OrderLineDto> dto = orderLineRepository.findByTransactIdTest(
                Integer.valueOf(customerId),
                        Integer.valueOf(orderId)
                )
                .map(OrderLineDto::new);
        return dto;
    }

}
