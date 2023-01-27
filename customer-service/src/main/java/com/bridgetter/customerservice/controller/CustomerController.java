package com.bridgetter.customerservice.controller;

import com.bridgetter.customerservice.model.dto.CustomerDto;
import com.bridgetter.customerservice.service.CustomerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = {"/customers" })
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Get All Customers" )
    })
    public Flux<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers() ;
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CustomerDto> getAllCustomersStream() {
        return customerService.getAllCustomers() ;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CustomerDto> getCustomer(@PathVariable("id") String id) {
        return customerService.getCustomerById(id) ;
    }

    @GetMapping(value = "{id}/orders/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Object> getCustomerOrder(@PathVariable("id") String id, @PathVariable("orderId") String orderId) {
        // This is bad, because the getCustomerOrders() method does not check to see
        // if the current customer is associated with that order id. This is due
        // to some issues I had with trying to validate the order in the target service,
        // thanks to the composite method chaining.

        return customerService.getCustomerOrderLines(id, orderId) ;
    }

    @GetMapping(value = "{id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Object> getCustomerOrders(@PathVariable("id") String id) {
        return customerService.getCustomerOrders(id) ;
    }


}
