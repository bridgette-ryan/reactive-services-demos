package com.bridgetter.customerservice.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {
    private String productId ;
    private Integer quantity ;
    private Double  priceAtOrdertime ;

}
