package com.bridgetter.customerservice.model.dto;


import com.bridgetter.customerservice.entity.Customer;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private Integer id ;
    private String firstName ;
    private String lastName ;
    private String postalAddress ;
    private String emailAddress ;
    private String phoneNumber ;

    public CustomerDto(Customer c) {
        id = c.getId();
        firstName = c.getFirstName();
        lastName = c.getLastName();
        postalAddress = c.getPostalAddress();
        emailAddress = c.getEmailAddress();
        phoneNumber = c.getPhoneNumber();
    }

}
