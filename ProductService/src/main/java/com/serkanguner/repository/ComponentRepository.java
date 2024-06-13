package com.serkanguner.repository;

import com.serkanguner.entity.Component;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends MongoRepository<Component,String> {
}
