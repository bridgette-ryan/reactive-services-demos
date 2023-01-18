package com.bridgetter.productsservice.exception;


public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String e) {
        super(e) ;
    }
    public ProductAlreadyExistsException() {

    }
}
