package com.company.retailprocessor.service;

import com.company.retailprocessor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    /***
     * return user object from data store matches username
     * @param name
     * @return User
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException
    {
        com.company.retailprocessor.model.User myUser = userRepository.findByUserName(name);
        if(myUser != null)
        {
            return new User(myUser.getUserName(), myUser.getPassword(), new ArrayList<>());
        }
        else
        {
            throw new UsernameNotFoundException("Invalid UserName or password");
        }
    }
}
