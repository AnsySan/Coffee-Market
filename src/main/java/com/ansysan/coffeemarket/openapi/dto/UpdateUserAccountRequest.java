package com.ansysan.coffeemarket.openapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserAccountRequest {

    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private AddressDto address;
    private LocalDate birthDate;

}
