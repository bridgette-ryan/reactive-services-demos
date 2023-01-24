package com.bridgetter.orderservice.model;

import com.bridgetter.orderservice.entity.Order;
import lombok.*;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private int transactId ;
    private int customerId ;
    private LocalDateTime orderDate ;

    public OrderDto(Order o) {
        transactId = o.getTransactId();
        customerId = o.getCustomerId();
        orderDate = o.getOrderDate();
    }
}
