package com.serkanguner.service;

import com.serkanguner.entity.Menu;
import com.serkanguner.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }



    public Optional<Menu> getMenuById(String id) {
        return menuRepository.findById(id);
    }

    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateMenu(String id, Menu menu) {
        if (menuRepository.existsById(id)) {
            menu.setId(id);
            return menuRepository.save(menu);
        } else {
            return null;
        }
    }

    public void deleteMenu(String id) {
        menuRepository.deleteById(id);
    }

    public List<Menu> getMenusByCategoryId(String categoryId) {
        return menuRepository.findByCategoryId(categoryId);
    }
}

