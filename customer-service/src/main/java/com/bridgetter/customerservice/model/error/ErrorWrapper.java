package com.bridgetter.customerservice.model.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ErrorWrapper {
    private List<BaseError> errors;
    public ErrorWrapper() {
        errors = new ArrayList<>();
    }
}
