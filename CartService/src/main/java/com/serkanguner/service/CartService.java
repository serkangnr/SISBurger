package com.serkanguner.service;

import com.serkanguner.dto.request.CartRequestDto;
import com.serkanguner.entity.Cart;
import com.serkanguner.entity.CartStatus;
import com.serkanguner.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    @Transactional
    @RabbitListener(queues = "queue.cart")
    public void sepeteEkle(CartRequestDto dto) {
        Cart cart = Cart.builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .description(dto.getDescription())
                .productId(dto.getMenuItemid())
                .quantity(dto.getQuantity())
                .components(dto.getComponents())
                .options(dto.getOptions())
                .totalPrice(dto.getPrice())
                .build();
        cartRepository.save(cart);


    }

    public List<Cart> getCart(String userId) {
        List<Cart> cartList = cartRepository.findAllByUserId(userId);
        List<Cart> newList = new ArrayList<>();
        cartList.forEach(cart -> {
            if (cart.getCartStatus().equals(CartStatus.ACTIVE)) {
                newList.add(cart);
            }
        });


        return newList;
    }

    public String updateQuantity(String cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.setQuantity(quantity);
        cartRepository.save(cart);
        return "Quantity updated";
    }

    public String deleteCart(String cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.setCartStatus(CartStatus.DELETED);
        return "Cart deleted";
    }

    public String cartConfirmation(String cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.setTotalPrice(cart.getTotalPrice() * cart.getQuantity());
        cart.setCartStatus(CartStatus.DELETED);
        cartRepository.save(cart);
        return "Cart confirmed";

    }
    public CartRequestDto cartConfirmationDto(String cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cart.setTotalPrice(cart.getTotalPrice() * cart.getQuantity());
        cart.setCartStatus(CartStatus.DELETED);
        cartRepository.save(cart);
        CartRequestDto cartRequestDto = CartRequestDto.builder()
                .name(cart.getName())
                .description(cart.getDescription())
                .userId(cart.getUserId())
                .menuItemid(cart.getProductId())
                .components(cart.getComponents())
                .options(cart.getOptions())
                .quantity(cart.getQuantity())
                .price(cart.getTotalPrice())
                .build();
        return cartRequestDto;

    }
    public String cartConfirmationByUserId(String userId) {
        List<Cart> cartList = cartRepository.findAllByUserId(userId);
        cartList.forEach(cart -> {
            cart.setTotalPrice(cart.getTotalPrice() * cart.getQuantity());
            cart.setCartStatus(CartStatus.DELETED);
            cartRepository.save(cart);
        });
        return "Cart confirmed";
    }
}
