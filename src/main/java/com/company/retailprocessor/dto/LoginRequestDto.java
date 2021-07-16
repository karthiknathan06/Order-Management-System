package com.company.retailprocessor.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoginRequestDto
{
    private String username;
    private String password;

    public LoginRequestDto(){}
    public LoginRequestDto(String userName, String password) {
        this.username = userName;
        this.password = password;
    }
}
