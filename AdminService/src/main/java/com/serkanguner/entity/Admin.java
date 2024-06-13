package com.serkanguner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class Admin {
    @MongoId
    String id;
    String name;
    String password;
    @Builder.Default
    Role role = Role.ADMIN;



}
