package com.bridgetter.customerservice.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("customers")
@Builder
public class Customer {
    @Id
    private Integer id ;
    private String firstName ;
    private String lastName ;
    private String postalAddress ;
    private String emailAddress ;
    private String phoneNumber ;

}
