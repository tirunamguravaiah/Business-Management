package com.business.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.entities.Orders;
import com.business.entities.User;
import com.business.repositories.OrderRepository;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository orderRepository;

    // Get all orders
    public List<Orders> getOrders() {
        return orderRepository.findAll();
    }

    // Save order
    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }

    // Update order
    public void updateOrder(String id, Orders order) {
        order.setoId(id); // MongoDB _id is a String
        orderRepository.save(order); // save() acts as upsert in Mongo
    }

    // Delete order
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    // Get order history for a specific user
    public List<Orders> getOrdersForUser(User user) {
        return orderRepository.findOrdersByUser(user);
    }
}
