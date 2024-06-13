package com.serkanguner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartRequestDto {
    String menuItemid;
    String name;
    String description;
    int quantity;
    Double price;
    List<String> components;
    List<String> options;
    String userId;
}
