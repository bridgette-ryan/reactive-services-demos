package com.bridgetter.customerservice.config;

import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RouterOperations(
            @RouterOperation(
                    path = "/customers",
                    produces = {MediaType.APPLICATION_JSON_VALUE} ,
                    method = RequestMethod.GET,
                    beanClass = CustomerRequestHandler.class,
                    beanMethod = "getAllCustomers",
                    operation = @Operation(
                            operationId = "getAllCustomers",
                            responses = {})
            )
    )
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
