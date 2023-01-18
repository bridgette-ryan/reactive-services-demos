package com.bridgetter.productsservice.service;

import com.bridgetter.productsservice.model.dto.ProductDto;
import com.bridgetter.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository ;

    public Flux<ProductDto> getAll() {
        return productRepository.findAll().map(ProductDto::new);
    }

    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id).map(ProductDto::new) ;
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> in) {
            return in.map(ProductDto::toEntity)
                    .flatMap(productRepository::insert)
                    .map(ProductDto::new) ;
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> in) {
        return productRepository.findById(id)
                .flatMap(product -> in
                        .map(ProductDto::toEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(ProductDto::new);
    }
}
