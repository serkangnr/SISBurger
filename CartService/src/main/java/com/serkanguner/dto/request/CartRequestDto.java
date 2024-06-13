package com.serkanguner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartRequestDto {
    String menuItemid; // urunId
    String name;
    String description;
    Double price;
    List<String> components;
    List<String> options;
    int quantity;
    String userId;
}
