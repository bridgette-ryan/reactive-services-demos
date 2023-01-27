package com.bridgetter.customerservice;

import com.bridgetter.customerservice.config.CustomerRequestHandler;
import com.bridgetter.customerservice.model.dto.CustomerDto;
import com.bridgetter.customerservice.service.CustomerService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
class CustomerServiceTests {

    @InjectMocks
    CustomerRequestHandler customerRequestHandler ;

    @Mock
    CustomerService customerService;

    WebTestClient webTestClient;


    List<CustomerDto> customers;

    @BeforeEach
    public void setup() {
        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(RequestPredicates.GET("/customers"),customerRequestHandler::getAllCustomers) ;
        webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build() ;

        customers = new ArrayList<>() ;
        customers.add(CustomerDto
                .builder()
                .id(1)
                .firstName("Bob")
                .lastName("Marley")
                .emailAddress("bob.marley@gmail.com")
                .phoneNumber("1234567890")
                .postalAddress("1 Bob Marley Lane")
                .build()) ;
        customers.add(CustomerDto
                .builder()
                .id(2)
                .firstName("Elton")
                .lastName("John")
                .emailAddress("elton.john@gmail.com")
                .phoneNumber("2345678901")
                .postalAddress("2 Elton John Road")
                .build()) ;
        customers.add(CustomerDto
                .builder()
                .id(3)
                .firstName("Marvin")
                .lastName("Gaye")
                .emailAddress("marvin.gaye@gmail.com")
                .phoneNumber("3456789012")
                .postalAddress("3 Marvin Gay Street")
                .build()) ;
    }

    @Test
    public void givenNothingReturnsFluxOfCustomers() {
        given(customerService.getAllCustomers())
                .willReturn(Flux.fromIterable(customers)) ;

        webTestClient.get()
                .uri("/customers")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json(new Gson().toJson(customers)) ;
    }



}
