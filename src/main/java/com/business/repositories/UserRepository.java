package com.business.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.business.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

    // Custom query method to find user by email
    User findUserByUemail(String email);
}
