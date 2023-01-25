package com.bridgetter.customerservice.config;

import com.bridgetter.customerservice.exception.DownstreamServiceUnavailableException;
import com.bridgetter.customerservice.model.error.BaseError;
import com.bridgetter.customerservice.model.error.DownstreamServiceUnavailable;
import com.bridgetter.customerservice.model.error.ErrorCodes;
import com.bridgetter.customerservice.model.error.ErrorWrapper;
import com.google.gson.Gson;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        DataBufferFactory dbf = exchange.getResponse().bufferFactory();
        ErrorWrapper errors = new ErrorWrapper();

        if(ex instanceof DownstreamServiceUnavailableException) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            errors.getErrors().add(new DownstreamServiceUnavailable(
                    HttpStatus.BAD_REQUEST,
                    exchange.getRequest().getPath().toString(),
                    ex.getMessage()
            )) ;
        }
        else {
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            errors.getErrors().add(new BaseError(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ErrorCodes.UNKNOWN_ERROR,
                    exchange.getRequest().getPath().toString(),
                    "Internal Server Error",
                    ex.getMessage()
            )) ;
        }

        DataBuffer errorMessage = dbf.wrap(new Gson().toJson(errors).getBytes()) ;
        return exchange.getResponse().writeWith(Mono.just(errorMessage));
    }
}
