package com.company.retailprocessor.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "order")
@Getter
@Setter
public class Order
{
    @Id
    private String orderId;
    private String userId;
    private String productId;
    private String status;
    private Address address;

    public Order(String userId, String productId, Address address, String status) {
        this.orderId = UUID.randomUUID().toString();
        this.userId = userId;
        this.productId = productId;
        this.status = status;
        this.address = address;
    }
}
