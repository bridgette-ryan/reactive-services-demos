package com.bridgetter.productsservice.model.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ProductAlreadyExistsError extends BaseError {

    private static String title = "Record already exists." ;
    public ProductAlreadyExistsError(HttpStatus status, String id, String source) {
        super(status,
                source,
                title,
                "The record " + id + " already exists within the datasource.");
    }
}
