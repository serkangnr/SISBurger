package com.serkanguner.repository;

import com.serkanguner.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin,String> {
    Admin findByName(String name);
}
