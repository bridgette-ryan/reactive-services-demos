package com.bridgetter.orderservice.config;

import com.bridgetter.orderservice.model.OrderDto;
import com.bridgetter.orderservice.model.OrderLineDto;
import com.bridgetter.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderRequestHandler {
    private final OrderService orderService;

    public OrderRequestHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public Mono<ServerResponse> getOrdersByCustomerId(ServerRequest serverRequest) {
        return ServerResponse.ok().body(orderService.getOrdersByCustomerId(serverRequest.pathVariable("id")), OrderDto.class) ;
    }

    public Mono<ServerResponse> getOrderLinesByOrderId(ServerRequest serverRequest) {
        Flux<OrderLineDto> dtoFlux = orderService.getOrderLinesByIdAndCustomerId(serverRequest.pathVariable("id")) ;
        return ServerResponse.ok().body(dtoFlux, OrderLineDto.class) ;
    }
}
