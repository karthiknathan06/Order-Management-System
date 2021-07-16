package com.company.retailprocessor.controller;

import com.company.retailprocessor.model.Order;
import com.company.retailprocessor.service.OrderService;
import com.company.retailprocessor.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
@Transactional
public class OrderControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ProductService productService;

    @Test
    @WithMockUser("testuser1")
    public void getAllOrder() throws Exception
    {
        Mockito.when(orderService.getOrders(Mockito.anyString()))
                .thenReturn(new ArrayList<>());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/");
        MvcResult result = mockMvc.perform(requestBuilder)
                            .andExpect(status().isOk())
                .           andReturn();
        assertEquals("[]", result.getResponse().getContentAsString());

    }

    @Test
    @WithMockUser("testuser1")
    public void getAllOrderWithList() throws Exception
    {
        List<Order> orderList = Collections.singleton(
                new Order("1213821321937213", "2132432543545", null,null)
        ).stream().collect(Collectors.toList());

        Mockito.when(orderService.getOrders(Mockito.anyString()))
                .thenReturn(orderList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/");
        mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$.[0].length()", is(5)))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser("testuser1")
    public void placeOrderTestWithProduct() throws Exception
    {
        Mockito.when(productService.isProductPresent(Mockito.anyString()))
                .thenReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"productId\":\"1b5761e1-25bd-42de-a9ce-34e28e48902c\",\n" +
                        "    \"doorNo\": \"74/199\",\n" +
                        "    \"streetName\": \"hi\",\n" +
                        "    \"landmark\": \"vkv stores\",\n" +
                        "    \"city\": \"madurai\",\n" +
                        "    \"state\": \"tn\",\n" +
                        "    \"pincode\":\"625020\"\n" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isConflict())
                .andExpect(content().string("Product Not Found"));
    }

    @Test
    @WithMockUser("testuser1")
    public void placeOrderTestWithoutProduct() throws Exception
    {
        Mockito.when(productService.isProductPresent(Mockito.anyString()))
                .thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"productId\":\"1b5761e1-25bd-42de-a9ce-34e28e48902c\",\n" +
                        "    \"doorNo\": \"74/199\",\n" +
                        "    \"streetName\": \"hi\",\n" +
                        "    \"landmark\": \"vkv stores\",\n" +
                        "    \"city\": \"madurai\",\n" +
                        "    \"state\": \"tn\",\n" +
                        "    \"pincode\":\"625020\"\n" +
                        "}");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("Order Placed"));
    }

    public void placeOrderTestWithValidation()
    {
        //Need to Check validation for the fields
    }
}