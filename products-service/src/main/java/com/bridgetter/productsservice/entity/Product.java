package com.bridgetter.productsservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("products")
@Builder
public class Product {
    @Id
    private String id;
    @Field("man_part_no")
    private String manufacturerPartNumber ;
    private String description ;
    @Field("stock")
    private Integer stockLevel ;
    private Double price ;
    private String image ;
}
