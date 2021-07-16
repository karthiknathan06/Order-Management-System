package com.company.retailprocessor.dto;

import com.company.retailprocessor.validator.ValidEmail;
import com.company.retailprocessor.validator.ValidPassword;
import com.company.retailprocessor.validator.ValidUserName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class SignUpDto
{
    @ValidUserName
    private String username;

    @NotNull
    @NotBlank
    @Size(min=1, max=30)
    private String firstname;

    @NotNull
    @Size(min=1, max=30)
    private String lastname;

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;
}
