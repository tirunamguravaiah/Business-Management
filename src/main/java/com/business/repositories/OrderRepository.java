package com.business.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.business.entities.Orders;
import com.business.entities.User;

public interface OrderRepository extends MongoRepository<Orders, String> {

    // Custom query method to find all orders by a specific user
    List<Orders> findOrdersByUser(User user);
}
