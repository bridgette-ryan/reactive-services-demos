package com.bridgetter.customerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    private final CustomerRequestHandler customerRequestHandler ;

    public RouterConfig(CustomerRequestHandler customerRequestHandler) {
        this.customerRequestHandler = customerRequestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
                .GET("customers/stream", customerRequestHandler::getAllCustomersStream)
                .GET("customers/{id}/orders/{orderId}", customerRequestHandler::getCustomerOrderLines)
                .GET("customers/{id}/orders", customerRequestHandler::getCustomerOrders)
                .GET("customers/{id}", customerRequestHandler::getCustomer)
                .GET("customers", customerRequestHandler::getAllCustomers)
                .build() ;
    }
}
