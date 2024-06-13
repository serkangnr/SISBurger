package com.serkanguner.repository;

import com.serkanguner.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart,String> {
    List<Cart> findAllByUserId(String userId);
}
