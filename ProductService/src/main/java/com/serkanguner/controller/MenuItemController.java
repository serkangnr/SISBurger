package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.entity.MenuItem;
import com.serkanguner.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EndPoints.MENUITEM)
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping(EndPoints.FINDALL)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping(EndPoints.FINDBYID)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<MenuItem> getMenuItemById(@RequestParam String id) {
        Optional<MenuItem> menuItem = menuItemService.getMenuItemById(id);
        return menuItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(EndPoints.SAVE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.addMenuItem(menuItem);
    }

    @PutMapping(EndPoints.UPDATE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<MenuItem> updateMenuItem(@RequestParam String id, @RequestBody MenuItem menuItem) {
        MenuItem updatedMenuItem = menuItemService.updateMenuItem(id, menuItem);
        return updatedMenuItem != null ? ResponseEntity.ok(updatedMenuItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(EndPoints.DELETE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteMenuItem(@RequestParam String id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
