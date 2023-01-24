package com.bridgetter.productsservice.model.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ProductPurchaseDto {
    private String id;
    private Integer count ;
}
