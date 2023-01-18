package com.bridgetter.productsservice.config;

import com.bridgetter.productsservice.exception.ProductAlreadyExistsException;
import com.bridgetter.productsservice.model.error.ProductAlreadyExistsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

@Configuration
public class RouterConfig {

    @Autowired
    private ProductRequestHandler productRequestHandler ;

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunctions() {
        return RouterFunctions.route()
                .GET("products/stream", productRequestHandler::getAllStream)
                .GET("products/{id}", productRequestHandler::getById)
                .GET("products", productRequestHandler::getAll)
                .POST("products", productRequestHandler::insertProduct)
                .PUT("products/{id}", productRequestHandler::updateProduct)
                .onError(ProductAlreadyExistsException.class, productAlreadyExistsHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> productAlreadyExistsHandler() {
        return (err, req) -> {
            ProductAlreadyExistsException ex = (ProductAlreadyExistsException) err;
            ProductAlreadyExistsResponse response = ProductAlreadyExistsResponse.builder().timestamp(LocalDateTime.now()).build();
            return ServerResponse.badRequest().bodyValue(response) ;
        } ;
    }
}
