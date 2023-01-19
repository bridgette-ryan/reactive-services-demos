package com.bridgetter.productsservice.model.error;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BaseError {
    private HttpStatus status;
    private String source ;
    private String title ;
    private String description ;
}
