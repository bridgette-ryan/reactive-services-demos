package com.bridgetter.productsservice.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class ProductPurchaseResponseDto {
    private String id;
    private String state ;

    public void setFailed() {
        state = "INSUFFICIENT STOCK" ;
    }

    public void setSuccess() {
        state = "SUCCESS" ;
    }
}
