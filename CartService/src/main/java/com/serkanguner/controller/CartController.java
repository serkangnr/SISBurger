package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.CartRequestDto;
import com.serkanguner.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping(EndPoints.CART)
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;




    @GetMapping(EndPoints.FINDALL)
    public ResponseEntity<List<CartRequestDto>> getCart(){
        return ResponseEntity.ok(cartService.getCart());
    }
    @PutMapping(EndPoints.UPDATE)
    public ResponseEntity<String> updateCart(@RequestParam(name = "cartId") String cartId, @RequestParam(name = "queantity") int quantity){
        return ResponseEntity.ok(cartService.updateQuantity(cartId, quantity));
    }
    @DeleteMapping(EndPoints.DELETE)
    public ResponseEntity<String> deleteCart(@RequestParam(name = "cartId") String cartId){
        return ResponseEntity.ok(cartService.deleteCart(cartId));
    }
    @PostMapping(EndPoints.CONFIRMATION)
    public ResponseEntity<String> addCart(@RequestParam String cartId){
        return ResponseEntity.ok(cartService.cartConfirmation(cartId));
    }




}
