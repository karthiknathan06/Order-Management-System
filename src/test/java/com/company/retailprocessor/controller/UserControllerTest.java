package com.company.retailprocessor.controller;

import com.company.retailprocessor.dto.SignUpDto;
import com.company.retailprocessor.enums.UserRole;
import com.company.retailprocessor.model.User;
import com.company.retailprocessor.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class UserControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void getListWithNoUser() throws Exception
    {
        Mockito.when(userService.getAllUsers())
                .thenReturn(new ArrayList<>());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        assertEquals("No User Found", result.getResponse().getContentAsString());
    }

    @Test
    public void getListWithUsers() throws Exception
    {
        List<User> userList = new ArrayList<>();
        //Json inputs need to be in separate json file
        User user1 = new User("Karthik", "Karthik", "nathan", "ckn@gmail.com", "karthikD@123", UserRole.USER);
        userList.add(user1);

        Mockito.when(userService.getAllUsers())
                .thenReturn(userList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].userName").value("Karthik"))
                .andExpect(jsonPath("$.[0].firstName").value("Karthik"))
                .andExpect(jsonPath("$.[0].lastName").value("nathan"))
                .andExpect(jsonPath("$.[0].email").value("ckn@gmail.com"))
                .andExpect(jsonPath("$.[0].password").value("karthikD@123"));
    }


    @Test
    public void addUser() throws Exception
    {
        SignUpDto userMock = Mockito.mock(SignUpDto.class);
        Mockito.when(userService.addUser(userMock))
                    .thenReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signUp")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\n" +
                                                    "    \"username\":\"karthikraja\",\n" +
                                                    "    \"password\":\"karthikraja@Dr1\",\n" +
                                                    "    \"email\":\"ckn12@gmail.com\",\n" +
                                                    "    \"firstname\":\"ranjith\",\n" +
                                                    "    \"lastname\":\"kumar\"\n" +
                                                    "}")
                                            .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                            .andExpect(status().isOk())
                            .andReturn();
        assertEquals("User Added", result.getResponse().getContentAsString());
    }

    //Need to Include unit test cases for validation checks
    public void addUserWithValidation()
    {

    }
}