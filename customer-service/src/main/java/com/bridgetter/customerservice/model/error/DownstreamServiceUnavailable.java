package com.bridgetter.customerservice.model.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DownstreamServiceUnavailable extends BaseError {

    private static String title = "Downstream Service Unavailable." ;
    public DownstreamServiceUnavailable(HttpStatus status, String source, String message) {
        super(status,
                ErrorCodes.DOWNSTREAM_SERVICE_UNAVAILABLE,
                source,
                title,
                message);
    }
}
