package com.serkanguner.repository;

import com.serkanguner.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByName(String name);

    User findByEmail(String email);

    User findByActivationCode(String activationCode);

    Boolean existsByEmail(String email);
}
