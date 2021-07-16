package com.company.retailprocessor.service;

import com.company.retailprocessor.config.KafkaMessageProducer;
import com.company.retailprocessor.dto.PlaceOrderDto;
import com.company.retailprocessor.model.Address;
import com.company.retailprocessor.model.AddressBuilder;
import com.company.retailprocessor.model.Order;
import com.company.retailprocessor.repository.OrderRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    KafkaMessageProducer kafkaMessageProducer;

    /***
     * Gets all available orders for given user
     * @param userName
     * @return List<Order>
     */
    public List<Order> getOrders(String userName)
    {
        return orderRepository.findAll();
    }

    /***
     * Adds productId, userId, status entries in order document
     * Updates orderId in kafka broker
     * @param placeOrderDto Product's unique identifier
     */
    public void placeOrder(PlaceOrderDto placeOrderDto, String userName)
    {
        String userId = userService.getUserByName(userName).getUserId();

        //builder pattern implementation
        Address address = new AddressBuilder()
                                .setDoorNo(placeOrderDto.getDoorNo())
                                .setCity(placeOrderDto.getCity())
                                .setLandmark(placeOrderDto.getLandmark())
                                .setPincode(placeOrderDto.getPincode())
                                .setState(placeOrderDto.getState())
                                .setAddress();

        Order order = new Order(userId, placeOrderDto.getProductId(), address, "Placed");
        orderRepository.save(order);

        JSONObject orderDetails = new JSONObject();
        orderDetails.put("orderId", order.getOrderId());
        orderDetails.put("userId", order.getUserId());

        kafkaMessageProducer.sendMessage(orderDetails);
    }

    /***
     * status field in order document provided orderId
     * @param orderId Unique Identifier for each order
     * @return String
     */
    public String getStatusById(String orderId)
    {
        return orderRepository.findByOrderId(orderId).getStatus();
    }

    /***
     * Updates the status message
     * @param orderId Unique Identifier for each order
     */
    public void updateStatusById(String orderId, String message)
    {
        Order order = orderRepository.findByOrderId(orderId);
        order.setStatus(message);
        orderRepository.save(order);
    }

    public boolean isOrderPresent(String orderId)
    {
        return orderRepository.findByOrderId(orderId) != null;
    }
}
