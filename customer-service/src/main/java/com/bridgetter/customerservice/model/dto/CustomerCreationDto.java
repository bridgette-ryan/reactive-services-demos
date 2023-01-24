package com.bridgetter.customerservice.model.dto;

import com.bridgetter.customerservice.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCreationDto {
    private String firstName ;
    private String lastName ;
    private String postalAddress ;
    private String emailAddress ;
    private String phoneNumber ;

    public Customer toEntity() {
        return Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .postalAddress(postalAddress)
                .emailAddress(emailAddress)
                .phoneNumber(phoneNumber)
                .build() ;
    }
}
