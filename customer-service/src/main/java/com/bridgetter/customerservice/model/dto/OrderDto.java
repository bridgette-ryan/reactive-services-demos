package com.bridgetter.customerservice.model.dto;

import lombok.*;

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
}
