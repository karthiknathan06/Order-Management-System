package com.company.retailprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address
{
    private String doorNo;
    private String streetName;
    private String landmark;
    private String city;
    private String state;
    private String pincode;
}
