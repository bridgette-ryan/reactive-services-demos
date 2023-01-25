package com.bridgetter.customerservice.exception;

public class DownstreamServiceUnavailableException extends RuntimeException{
    public DownstreamServiceUnavailableException(String service) {
        super("Downstream Service " + service + " is unavailable at this time.") ;
    }
}
