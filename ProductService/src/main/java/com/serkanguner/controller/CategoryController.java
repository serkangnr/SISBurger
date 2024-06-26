package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.entity.Category;

import com.serkanguner.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EndPoints.CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(EndPoints.FINDALL)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(EndPoints.FINDCATEGORIESBYID)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Category> getCategoryById(@RequestParam String id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(EndPoints.SAVE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping(EndPoints.UPDATE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Category> updateCategory(@RequestParam String id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(EndPoints.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@RequestParam String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


}
