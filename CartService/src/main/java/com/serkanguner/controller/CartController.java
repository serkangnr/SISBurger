package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.CartRequestDto;
import com.serkanguner.entity.Cart;
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
    public ResponseEntity<List<Cart>> getCart(String userId){
        return ResponseEntity.ok(cartService.getCart(userId));
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
    public ResponseEntity<String> confirmCart(@RequestParam String cartId){
        return ResponseEntity.ok(cartService.cartConfirmation(cartId));
    }
    @PostMapping(EndPoints.CONFIRMATIONDTO)
    public ResponseEntity<CartRequestDto> confirmCartDto(@RequestParam String cartId){
        return ResponseEntity.ok(cartService.cartConfirmationDto(cartId));
    }
    @PostMapping(EndPoints.CONFIRMATIONBYUSERID)
    public ResponseEntity<String> confirmCartByUserId(@RequestParam String userId){
        return ResponseEntity.ok(cartService.cartConfirmationByUserId(userId));
    }




}
