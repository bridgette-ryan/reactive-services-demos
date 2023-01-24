package com.bridgetter.orderservice.model;

import com.bridgetter.orderservice.entity.OrderLine;
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

    public OrderLineDto(OrderLine line) {
        productId = line.getProductId();
        quantity = line.getQuantity();
        priceAtOrdertime = line.getPriceAtOrdertime();
    }
}
