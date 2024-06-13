package com.serkanguner.service;

import com.serkanguner.dto.request.TokenReturnDto;
import com.serkanguner.entity.Category;
import com.serkanguner.exception.ErrorType;
import com.serkanguner.exception.ProductServiceException;
import com.serkanguner.repository.CategoryRepository;
import com.serkanguner.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final JwtTokenManager jwtTokenManager;
    @Value("${productservice.secret.admin-name}")
    private String adminName;
    @Value("${productservice.secret.admin-password}")
    private String adminPassword;

    public TokenReturnDto tokenCheck(String token) {
        String name = jwtTokenManager.getNameFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_TOKEN));
        String password = jwtTokenManager.getPasswordFromToken(token).orElseThrow(() -> new ProductServiceException(ErrorType.INVALID_TOKEN));
        return TokenReturnDto.builder()
                .name(name)
                .password(password)
                .build();

    }


    public List<Category> getAllCategories() {
//        TokenReturnDto dto = tokenCheck(token);
//        if (adminName.equals(dto.getName()) && adminPassword.equals(dto.getPassword())) {
        return categoryRepository.findAll();
//        }else {
//            throw new ProductServiceException(ErrorType.INVALID_UNAUTHORIZED);
    }


    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(String id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        } else {
            return null;
        }
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}


