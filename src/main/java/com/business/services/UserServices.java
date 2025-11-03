package com.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.business.entities.User;
import com.business.repositories.UserRepository;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get single user by ID
    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    // Get single user by email
    public User getUserByEmail(String email) {
        return userRepository.findUserByUemail(email);
    }

    // Add new user
    public void addUser(User user) {
        userRepository.save(user);
    }

    // Update user
    public void updateUser(User user, String id) {
        if (userRepository.existsById(id)) {
            user.setU_id(id);
            userRepository.save(user);
        }
    }

    // Delete user
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    // Validate login credentials
    public boolean validateLoginCredentials(String email, String password) {
        User user = userRepository.findUserByUemail(email);
        return user != null && user.getUpassword().equals(password);
    }
}
