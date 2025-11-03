package com.business.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.business.entities.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {

    // Custom query method (same as before)
    Admin findByAdminEmail(String email);
}
