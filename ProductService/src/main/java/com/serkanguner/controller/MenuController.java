package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.entity.Menu;
import com.serkanguner.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EndPoints.MENUS)
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping(EndPoints.FINDALL)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping(EndPoints.FINDBYID)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Menu> getMenuById(@RequestParam String id) {
        Optional<Menu> menu = menuService.getMenuById(id);
        return menu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(EndPoints.SAVE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Menu addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @PutMapping(EndPoints.UPDATE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Menu> updateMenu(@RequestParam String id, @RequestBody Menu menu) {
        Menu updatedMenu = menuService.updateMenu(id, menu);
        return updatedMenu != null ? ResponseEntity.ok(updatedMenu) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(EndPoints.DELETE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteMenu(@RequestParam String id) {
        menuService.deleteMenu(id);
        return ResponseEntity.noContent().build();
    }

}
