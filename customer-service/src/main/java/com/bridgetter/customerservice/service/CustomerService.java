package com.bridgetter.customerservice.service;

import com.bridgetter.customerservice.exception.DownstreamServiceUnavailableException;
import com.bridgetter.customerservice.model.dto.CustomerDto;
import com.bridgetter.customerservice.repository.CustomerRepository;
import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
public class CustomerService {
    @Value("${config.hosts.order-service}")
    private String orderServiceHost ;

    private final CustomerRepository customerRepository ;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Flux<CustomerDto> getAllCustomers() {
        return customerRepository
                .findAll()
                .map(CustomerDto::new);
    }

    public Mono<CustomerDto> getCustomerById(String id) {
        return customerRepository
                .findById(Integer.valueOf(id))
                .map(CustomerDto::new) ;
    }


    public Flux<Object> getCustomerOrders(String id) {
        return this.newWebClient()
                .get()
                .uri("/customer-orders/{id}", id )
                .retrieve()
                .bodyToFlux(Object.class)
                .onErrorMap(e -> {
                    if(e instanceof WebClientRequestException) return new DownstreamServiceUnavailableException("order") ;
                    return e ;
                });
    }

    public Flux<Object> getCustomerOrderLines(String id) {
        return this.newWebClient()
                .get()
                .uri("/order-lines/{id}", id )
                .retrieve()
                .bodyToFlux(Object.class)
                .onErrorMap(e -> {
                    if(e instanceof WebClientRequestException) return new DownstreamServiceUnavailableException("order") ;
                    return e ;
                });

    }

    // Each method generates its own HttpClient, ClientHttpConnector and WebClient,
    // as a class level one acts like a singleton.
    private WebClient newWebClient() {
        HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) ;
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient.wiretap(true)) ;

        return WebClient
                .builder()
                .clientConnector(connector)
                .baseUrl(orderServiceHost)
                .defaultHeader(
                        HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE)
                .build();

    }
}
