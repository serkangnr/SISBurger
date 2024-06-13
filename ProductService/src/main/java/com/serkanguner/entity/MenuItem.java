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
public class MenuItem {
    @MongoId
    private String id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private String menuId;
    private List<String> componentId;
    private List<String> optionId;



}
