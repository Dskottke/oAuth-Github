package com.example.backend.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<MongoUser, String> {

    Optional<MongoUser> findByEmail(String email);
}
