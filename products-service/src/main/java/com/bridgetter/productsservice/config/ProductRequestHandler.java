package com.bridgetter.productsservice.config;

import com.bridgetter.productsservice.entity.Product;
import com.bridgetter.productsservice.model.dto.ProductDto;
import com.bridgetter.productsservice.model.dto.ProductPurchaseDto;
import com.bridgetter.productsservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductRequestHandler {
    @Autowired
    private ProductService productService ;

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        Flux<ProductDto> allProducts = productService.getAll();
        return ServerResponse.ok()
                .body(allProducts, Product.class) ;
    }

    public Mono<ServerResponse> getAllStream(ServerRequest serverRequest) {
        Flux<ProductDto> allProducts = productService.getAll();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(allProducts, Product.class) ;
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        Mono<ProductDto> dto = productService.getProduct(serverRequest.pathVariable("id")) ;
        return dto
                .flatMap(product -> ServerResponse.ok().body(product, ProductDto.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> insertProduct(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .body(productService.insertProduct(serverRequest.bodyToMono(ProductDto.class)), ProductDto.class);
    }

    public Mono<ServerResponse> updateProduct(ServerRequest serverRequest) {
        Mono<ProductDto> dto = productService.updateProduct(serverRequest.pathVariable("id"),serverRequest.bodyToMono(ProductDto.class)) ;
        return ServerResponse.ok()
                .body(dto, ProductDto.class) ;
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest serverRequest) {
        return  productService.deleteProduct(
                        serverRequest.pathVariable("id"))
                .then(ServerResponse.noContent().build());
    }
}
