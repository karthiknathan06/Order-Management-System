package com.company.retailprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto
{
    @NotNull
    @NotBlank
    private String productId;
    @NotNull
    @NotBlank
    private String doorNo;
    private String streetName;
    @NotNull
    @NotBlank
    private String landmark;
    @NotNull
    @NotBlank
    private String city;
    @NotNull
    @NotBlank
    private String state;
    @NotNull
    @NotBlank
    private String pincode;
}
