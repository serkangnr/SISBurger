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
import java.util.concurrent.atomic.AtomicBoolean;
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


    public List<CartRequestDto> getCart() {
        List<Cart> cartList = cartRepository.findAll();
        AtomicBoolean deleted = new AtomicBoolean(false);
        AtomicReference<CartRequestDto> cartRequestDto = new AtomicReference<>();
        AtomicReference<List<CartRequestDto>> requestDto = new AtomicReference<>(new ArrayList<>());


        cartList.forEach(cart -> {
            cartRequestDto.set(CartRequestDto.builder()
                    .userId(cart.getUserId())
                    .name(cart.getName())
                    .description(cart.getDescription())
                    .menuItemid(cart.getProductId())
                    .quantity(cart.getQuantity())
                    .components(cart.getComponents())
                    .options(cart.getOptions())
                    .price(cart.getTotalPrice())
                    .build());
            CartRequestDto dto = cartRequestDto.getAcquire();

            requestDto.set(List.of(dto));
        });



        return requestDto.get();

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
}
