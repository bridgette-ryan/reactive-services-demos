package com.bridgetter.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    private final OrderRequestHandler orderRequestHandler ;

    public RouterConfig(OrderRequestHandler orderRequestHandler) {
        this.orderRequestHandler = orderRequestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
                .GET("order-lines/{id}",orderRequestHandler::getOrderLinesByOrderId)
                .GET("customer-orders/{id}",orderRequestHandler::getOrdersByCustomerId)
                .build();
    }
}
