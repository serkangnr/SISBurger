package com.serkanguner.dto.response;

import com.serkanguner.entity.Component;
import com.serkanguner.entity.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MenuItemResponseDto {
    String id;
    String name;
    String description;
    Double price;
    List<String> components;
    List<String> options;
    String info ;


}
