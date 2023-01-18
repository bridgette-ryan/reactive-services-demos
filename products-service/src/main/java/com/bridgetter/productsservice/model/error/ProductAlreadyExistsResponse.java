package com.bridgetter.productsservice.model.error;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ProductAlreadyExistsResponse {
    private static Integer errorCode = 100 ;
    private LocalDateTime timestamp ;
    private static final String MESSAGE = "Product ID already exists in dataset" ;
}
