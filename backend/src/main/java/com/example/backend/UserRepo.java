package com.example.backend;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<MongoUser, String> {

    Optional<MongoUser> findByUsername(String username);
}
