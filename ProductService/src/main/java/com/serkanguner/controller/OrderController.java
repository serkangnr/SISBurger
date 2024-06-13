package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.dto.request.ProductChooseRequestDto;
import com.serkanguner.dto.response.MenuItemResponseDto;
import com.serkanguner.entity.Category;
import com.serkanguner.entity.Menu;
import com.serkanguner.entity.MenuItem;
import com.serkanguner.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPoints.ORDER)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @GetMapping(EndPoints.CATEGORIESLIST)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<List<Category>> getCategoriesList() {
      return ResponseEntity.ok(orderService.getAllCategories()) ;
    }

    @GetMapping(EndPoints.MENUSLIST)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<Menu>> getMenusList(@RequestParam String categoryId) {
        return ResponseEntity.ok(orderService.getAllMenus(categoryId));
    }
    @GetMapping(EndPoints.URUNLISTELE)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<MenuItem>> getMenuItemList(@RequestParam String menuId) {
        return ResponseEntity.ok(orderService.getAllMenuItems(menuId));
    }
    @PostMapping(EndPoints.URUNSEC)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> addOrder(@RequestBody ProductChooseRequestDto dto) {
         ResponseEntity.ok(orderService.urunSec(dto));
         return ResponseEntity.ok("Urunler sepete eklendi.");
    }


}
