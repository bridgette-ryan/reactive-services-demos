package com.bridgetter.customerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

//@Configuration
public class RouterConfig {
    private final CustomerRequestHandler customerRequestHandler ;

    public RouterConfig(CustomerRequestHandler customerRequestHandler) {
        this.customerRequestHandler = customerRequestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return route()
                .path("customers", this::customersRouterFunction)
                .build() ;
    }

    @Bean
    public RouterFunction<ServerResponse> customersRouterFunction() {
        return route()
                .GET("stream", customerRequestHandler::getAllCustomersStream)
                .GET("{id}/orders/{orderId}", customerRequestHandler::getCustomerOrderLines)
                .GET("{id}/orders", customerRequestHandler::getCustomerOrders)
                .GET("{id}", customerRequestHandler::getCustomer)
                .GET(customerRequestHandler::getAllCustomers)
                .build();
    }
}