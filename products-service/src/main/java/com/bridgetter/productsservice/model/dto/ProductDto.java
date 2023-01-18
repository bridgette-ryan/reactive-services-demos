package com.bridgetter.productsservice.model.dto;

import com.bridgetter.productsservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String id;
    private String manufacturerPartNumber ;
    private String description ;
    private Integer stockLevel ;
    private Double price ;
    private String image ;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.manufacturerPartNumber = p.getManufacturerPartNumber();
        this.description = p.getDescription();
        this.stockLevel = p.getStockLevel();
        this.price = p.getPrice();
        this.image = p.getImage();
    }

    public Product toEntity() {
        return Product.builder()
                .id(id)
                .manufacturerPartNumber(manufacturerPartNumber)
                .description(description)
                .stockLevel(stockLevel)
                .price(price)
                .image(image)
                .build();
    }
}
