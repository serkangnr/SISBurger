package com.serkanguner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document
public class Cart {
    @MongoId
    private String id;
    private String name;
    private String description;
    private String userId;
    private String productId;
    private int quantity;
    private Double totalPrice;
    List<String> components;
    List<String> options;
    @Builder.Default
    private CartStatus cartStatus = CartStatus.ACTIVE;
}
