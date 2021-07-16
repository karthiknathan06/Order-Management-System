package com.company.retailprocessor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class AddProductDto
{
    @NotNull
    @NotBlank
    @Size(min=0, max=60)
    private String name;

    @NotNull
    @Size(min=0, max=100)
    private String description;

    @NotNull
    private Double cost;

    @NotNull
    @Size(min=0, max=10)
    private String category;
}
