package com.bridgetter.productsservice.exception;


import lombok.Getter;

@Getter
public class ProductAlreadyExistsException extends RuntimeException {

    private static String message = "Product Already Exists in Datasource" ;
    private String id ;
    public ProductAlreadyExistsException(String id) {
        super(message) ;
        this.id = id ;
    }
}
