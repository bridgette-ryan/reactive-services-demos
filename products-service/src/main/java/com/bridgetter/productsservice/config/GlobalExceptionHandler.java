package com.bridgetter.productsservice.config;

import com.bridgetter.productsservice.exception.ProductAlreadyExistsException;
import com.bridgetter.productsservice.model.error.BaseError;
import com.bridgetter.productsservice.model.error.ErrorCodes;
import com.bridgetter.productsservice.model.error.ErrorWrapper;
import com.bridgetter.productsservice.model.error.ProductAlreadyExistsError;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import com.google.gson.Gson;

@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        DataBufferFactory dbf = exchange.getResponse().bufferFactory();
        ErrorWrapper errors = new ErrorWrapper();

        if(ex instanceof ProductAlreadyExistsException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            errors.getErrors().add(new ProductAlreadyExistsError(
                    HttpStatus.BAD_REQUEST,
                    ((ProductAlreadyExistsException) ex).getId(),
                    exchange.getRequest().getPath().toString()
            )) ;
        }
        else {
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            errors.getErrors().add(new BaseError(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorCodes.PRODUCT_ALREADY_EXISTS,
                    exchange.getRequest().getPath().toString(),
                    "Internal Server Error",
                    ex.getMessage()
            )) ;
        }

        DataBuffer errorMessage = dbf.wrap(new Gson().toJson(errors).getBytes()) ;
        return exchange.getResponse().writeWith(Mono.just(errorMessage));
    }
}
