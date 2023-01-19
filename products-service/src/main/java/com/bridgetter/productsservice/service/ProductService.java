package com.bridgetter.productsservice.service;

import com.bridgetter.productsservice.exception.ProductAlreadyExistsException;
import com.bridgetter.productsservice.model.dto.ProductDto;
import com.bridgetter.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private final ProductRepository productRepository ;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<ProductDto> getAll() {
        return productRepository.findAll().map(ProductDto::new);
    }

    public Mono<ProductDto> getProduct(String id) {
        return productRepository.findById(id).map(ProductDto::new) ;
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> in) {
        var saved = in.map(ProductDto::toEntity)
                .flatMap( product -> productRepository.insert(product)
                        .onErrorMap(e -> {
                            if(e.getClass() == DuplicateKeyException.class) return new ProductAlreadyExistsException(product.getId()) ;
                            return e ;
                        })
                        ) ;
        return saved.map(ProductDto::new) ;
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> in) {
        return productRepository.findById(id)
                .flatMap(product -> in
                        .map(ProductDto::toEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(ProductDto::new);
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id) ;
    }
}
