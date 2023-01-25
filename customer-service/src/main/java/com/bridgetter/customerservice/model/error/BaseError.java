package com.bridgetter.customerservice.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
