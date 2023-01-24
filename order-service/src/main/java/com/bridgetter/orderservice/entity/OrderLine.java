package com.bridgetter.orderservice.entity;


import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("order_lines")
public class OrderLine {
    private int transactId ;
    private String productId ;
    private Integer quantity ;
    private Double  priceAtOrdertime ;
}
