package com.company.retailprocessor.dto;

public class LoginResponseDto
{
    private final String jwtToken;

    public String getToken()
    {
        return jwtToken;
    }

    public LoginResponseDto(String jwtToken)
    {
        this.jwtToken = jwtToken;
    }
}
