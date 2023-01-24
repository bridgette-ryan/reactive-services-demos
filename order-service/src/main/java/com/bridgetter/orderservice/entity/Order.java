package com.bridgetter.orderservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Table("orders")
public class Order {
    @Id
    private int transactId ;
    private int customerId ;
    private LocalDateTime orderDate ;
}
