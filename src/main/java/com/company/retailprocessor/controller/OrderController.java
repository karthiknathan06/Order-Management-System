package com.company.retailprocessor.controller;

import com.company.retailprocessor.dto.PlaceOrderDto;
import com.company.retailprocessor.model.Order;
import com.company.retailprocessor.service.OrderService;
import com.company.retailprocessor.service.ProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("order")
@RestController
public class OrderController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    /***
     * To return list of products else empty list
     * @param user
     * @return List of products
     */
    @GetMapping("/")
    public List<Order> getAllOrder(@AuthenticationPrincipal UserDetails user)
    {
        List<Order> orderList = orderService.getOrders(user.getUsername());
        if(orderList.size() > 0)
        {
            return orderList;
        }
        return new ArrayList<>();
    }

    /***
     * To place order if product Id is valid
     * @param placeOrderDto
     * @param user
     * @return status message
     */
    @PostMapping("/")
    public ResponseEntity placeOrder(@RequestBody PlaceOrderDto placeOrderDto, @AuthenticationPrincipal UserDetails user)
    {
        if(productService.isProductPresent(placeOrderDto.getProductId()))
        {
            orderService.placeOrder(placeOrderDto, user.getUsername());
            return new ResponseEntity<>("Order Placed", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }

    /***
     * To return status of order placed
     * @param orderId
     * @return status with corresponding orderId
     */
    @GetMapping("/status/{orderId}")
    public String getStatus(@PathVariable String orderId)
    {
        if(orderService.isOrderPresent(orderId))
        {
            return orderService.getStatusById(orderId);
        }
        return "Invalid Order Id";
    }
}
