package com.bridgetter.productsservice.model.error;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BaseError {
    private HttpStatus status;
    private Integer errorCode ;
    private String source ;
    private String title ;
    private String description ;


}
