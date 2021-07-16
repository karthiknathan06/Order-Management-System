package com.company.retailprocessor.controller;

import com.company.retailprocessor.dto.SignUpDto;
import com.company.retailprocessor.model.User;
import com.company.retailprocessor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    /***
     * To get list of users available
     * @return List of Users
     */
    @GetMapping("/users")
    public ResponseEntity getUsers()
    {
        List<User> users = userService.getAllUsers();
        if(users.size() > 0) {
            return new ResponseEntity(users, HttpStatus.OK);
        }
        return new ResponseEntity("No User Found", HttpStatus.NOT_FOUND);
    }

    /***
     * To create new account
     * @param user
     * @return Status message
     */
    @PostMapping("/signUp")
    public ResponseEntity addUser(@RequestBody @NotNull @Valid SignUpDto user)
    {
        userService.addUser(user);
        return new ResponseEntity("User Added", HttpStatus.CREATED);

    }
}
