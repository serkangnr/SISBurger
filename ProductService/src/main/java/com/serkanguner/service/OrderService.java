package com.serkanguner.service;

import com.serkanguner.config.security.JwtUserDetails;
import com.serkanguner.config.swagger.OpenApiConfig;
import com.serkanguner.dto.request.CartRequestDto;
import com.serkanguner.dto.request.ProductChooseRequestDto;
import com.serkanguner.dto.request.TokenReturnDto;
import com.serkanguner.dto.response.ComponentsResponseDto;
import com.serkanguner.dto.response.MenuItemResponseDto;
import com.serkanguner.entity.Category;
import com.serkanguner.entity.Menu;
import com.serkanguner.entity.MenuItem;
import com.serkanguner.entity.Order;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.exception.ProductServiceException;
import com.serkanguner.mapper.ProductMapper;
import com.serkanguner.repository.OrderRepository;
import com.serkanguner.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CategoryService categoryService;
    private final MenuService menuService;
    private final MenuItemService menuItemService;
    private final ComponentService componentService;
    private final OptionService optionService;
    private final JwtTokenManager jwtTokenManager;
    private final RabbitTemplate rabbitTemplate;







    public List<Category> getAllCategories() {
       return categoryService.getAllCategories();
    }
    public List<Menu> getAllMenus(String categoryId) {
        return menuService.getMenusByCategoryId(categoryId);
    }

    public List<MenuItem> getAllMenuItems(String menuId) {
        return menuItemService.getMenuItemsByMenuId(menuId);
    }

    public TokenReturnDto tokenCheck(String token) {
        String userId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_TOKEN));
        //String name = jwtTokenManager.getNameFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_TOKEN));
        //String password = jwtTokenManager.getPasswordFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_TOKEN));
        return TokenReturnDto.builder()
                .id(userId)
                .build();

    }

    @Transactional
    public String urunSec(ProductChooseRequestDto dto){ // olusturulan menuden secilmesi için olusturulan metod
        MenuItem menuItem = menuItemService.getMenuItemById(dto.getMenuItemId()).orElseThrow(()-> new ProductServiceException(ErrorType.PRODUCT_NOT_FOUND));
        List<String> componentId = menuItem.getComponentId();
        List<String> componentName = new ArrayList<>();
        componentId.forEach(component -> {
            componentName.add(componentService.getComponentById(component).get().getName());
        });
        List<String> optionId = menuItem.getOptionId();
        List<String> optionName = new ArrayList<>();
        optionId.forEach(option -> {
            optionName.add(optionService.getOptionById(option).get().getName());
        });

        TokenReturnDto tokenReturnDto = tokenCheck(dto.getToken());
        String userId = tokenReturnDto.getId();


        //repository'ye kaydolacak
        Order order = Order.builder()
                .userId(userId)
                .menuItemId(menuItem.getId())
                .quantity(dto.getQuantity())
                .totalPrice(menuItem.getPrice()*dto.getQuantity())
                .build();
        orderRepository.save(order);

        // Swagger donusu icin olusturuldu
        MenuItemResponseDto menuDto = MenuItemResponseDto.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .components(componentName)
                .options(optionName)
                .info("Urun Sepete Eklendi...")
                .build();

        // Rabbit ile sepete gonderilecek
        CartRequestDto cartRequestDto = CartRequestDto.builder()
                .menuItemid(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .quantity(dto.getQuantity())
                .price(menuItem.getPrice())
                .components(componentName)
                .options(optionName)
                .userId(userId)
                .build();

        rabbitTemplate.convertAndSend("exchange.direct", "Routing.cart", cartRequestDto);
        return "Sepete eklendi";

    }





}
