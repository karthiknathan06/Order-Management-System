package com.company.retailprocessor.service;

import com.company.retailprocessor.dto.SignUpDto;
import com.company.retailprocessor.enums.UserRole;
import com.company.retailprocessor.model.User;
import com.company.retailprocessor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepo;

    /***
     * To add user from dto
     * @param user
     * @return boolean flag
     */
    public boolean addUser(SignUpDto user)
    {
        User userObj = new User(user.getUsername().trim(), user.getFirstname().trim(), user.getLastname().trim()
                ,user.getEmail().trim(), user.getPassword().trim(), UserRole.USER);
        userRepo.save(userObj);
        return true;
    }

    /***
     * To delete used provided email
     * @param email
     * @return boolean flag
     */
    public boolean deleteUser(String email)
    {
        userRepo.deleteByEmail(email);
        return true;
    }

    /***
     * To return list of users available
     * @return List
     */
    public List<User> getAllUsers()
    {
        return userRepo.findAll();
    }

    /***
     * To return user provided username
     * @param name
     * @return User
     */
    public User getUserByName(String name)
    {
        return userRepo.findByUserName(name);
    }

    /***
     * To check valid user from email
     * @param email
     * @return boolean
     */
    public boolean ifUserExistForEmail(String email){ return userRepo.findByEmail(email) != null; }

    /***
     * To check valid user from user name
     * @param userName
     * @return boolean flag
     */
    public boolean ifUserExistForUserName(String userName){ return userRepo.findByUserName(userName) != null; }

}
