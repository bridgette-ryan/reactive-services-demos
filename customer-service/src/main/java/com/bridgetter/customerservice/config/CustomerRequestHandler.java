package com.bridgetter.customerservice.config;


import com.bridgetter.customerservice.model.dto.CustomerDto;
import com.bridgetter.customerservice.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class CustomerRequestHandler {
    private final CustomerService customerService;

    public CustomerRequestHandler(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Mono<ServerResponse> getAllCustomersStream(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerService.getAllCustomers(), CustomerDto.class) ;
    }

    public Mono<ServerResponse> getAllCustomers(ServerRequest serverRequest) {
        return ServerResponse.ok().body(customerService.getAllCustomers(), CustomerDto.class) ;
    }

    public Mono<ServerResponse> getCustomer(ServerRequest serverRequest) {
        Mono<CustomerDto> dto = customerService.getCustomerById(serverRequest.pathVariable("id")) ;

        return dto.flatMap(customerDto -> ServerResponse.ok().bodyValue(customerDto))
                .switchIfEmpty(ServerResponse.notFound().build()) ;
    }

    public Mono<ServerResponse> getCustomerOrders(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
                customerService.getCustomerOrders(serverRequest.pathVariable("id")),Object.class);
    }

    public Mono<ServerResponse> getCustomerOrderLines(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
                customerService.getCustomerOrderLines(
                        serverRequest.pathVariable("id"),
                        serverRequest.pathVariable("orderId")
                ),Object.class);
    }

}