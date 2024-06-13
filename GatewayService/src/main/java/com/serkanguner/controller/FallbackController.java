package com.serkanguner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/admin")
    public ResponseEntity<String> getFallbackAdmin(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("AdminService is not responding");
    }
    @GetMapping("/user")
    public ResponseEntity<String> getFallbackUser(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UserService is not responding");
    }
    @GetMapping("/product")
    public ResponseEntity<String> getFallbackProduct(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product is not responding");
    }
    @GetMapping("/cart")
    public ResponseEntity<String> getFallbackCart(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cart is not responding");
    }
}
