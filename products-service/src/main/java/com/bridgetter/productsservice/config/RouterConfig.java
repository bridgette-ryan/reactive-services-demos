package com.bridgetter.productsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    private final ProductRequestHandler productRequestHandler ;

    public RouterConfig(ProductRequestHandler productRequestHandler) {
        this.productRequestHandler = productRequestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunctions() {
        return RouterFunctions.route()
                .GET("products/stream", productRequestHandler::getAllStream)
                .GET("products/{id}", productRequestHandler::getById)
                .GET("products", productRequestHandler::getAll)
                .POST("products", productRequestHandler::insertProduct)
                .PUT("products/{id}", productRequestHandler::updateProduct)
                .DELETE("products/{id}", productRequestHandler::deleteProduct)
                .build();
    }
}
