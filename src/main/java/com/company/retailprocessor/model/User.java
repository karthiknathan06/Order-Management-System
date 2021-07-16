package com.company.retailprocessor.model;

import com.company.retailprocessor.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Document(collection = "user")
@Getter
@Setter
public class User
{
    @Id
    private String userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @NotEmpty
    @NotNull
    private UserRole role;

    public User(String userName, String firstName, String lastName, String email, String password, UserRole role)
    {
        this.userName = userName;
        this.userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
