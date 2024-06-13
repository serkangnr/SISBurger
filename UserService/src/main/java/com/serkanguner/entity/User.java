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
public class User {
    @MongoId
    String id;
    String name;
    String surname;
    String email;
    String phone;
    String password;
    String repassword;
    String activationCode;
    @Builder.Default
    Status status = Status.INACTIVE;
    @Builder.Default
    Role role = Role.USER;


}
