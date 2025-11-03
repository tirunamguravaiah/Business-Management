package com.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.business.entities.User;
import com.business.services.UserServices;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices services;

    // 🟩 Add User
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        this.services.addUser(user);
        return "redirect:/admin/services";
    }

    // 🟩 Update User
    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute User user, @PathVariable("id") String id) {
        this.services.updateUser(user, id);
        return "redirect:/admin/services";
    }

    // 🟩 Delete User
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        this.services.deleteUser(id);
        return "redirect:/admin/services";
    }
}
