package com.company.retailprocessor.controller;

import com.company.retailprocessor.dto.LoginRequestDto;
import com.company.retailprocessor.dto.LoginResponseDto;
import com.company.retailprocessor.service.MyUserDetailsService;
import com.company.retailprocessor.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    /***
     * If authenticated, create and return jwt token
     * @param loginRequestDto
     * @return ResponseEntity
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestDto loginRequestDto) throws Exception
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        //should be updated in cookies
        return ResponseEntity.ok(new LoginResponseDto(jwt));
    }
}
