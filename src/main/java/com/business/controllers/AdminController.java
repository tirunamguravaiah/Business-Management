package com.business.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.business.basiclogics.Logic;
import com.business.entities.Admin;
import com.business.entities.Orders;
import com.business.entities.Product;
import com.business.entities.User;
import com.business.loginCredentials.AdminLogin;
import com.business.loginCredentials.UserLogin;
import com.business.services.AdminServices;
import com.business.services.OrderServices;
import com.business.services.ProductServices;
import com.business.services.UserServices;

@Controller
public class AdminController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private AdminServices adminServices;

    @Autowired
    private ProductServices productServices;

    @Autowired
    private OrderServices orderServices;

    private String email;
    private User user;

    // 🟩 Admin Login Validation
    @GetMapping("/adminLogin")
    public String getAllData(@ModelAttribute("adminLogin") AdminLogin login, Model model) {
        String email = login.getEmail();
        String password = login.getPassword();

        if (adminServices.validateAdminCredentials(email, password)) {
            return "redirect:/admin/services";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "Login";
        }
    }

    // 🟩 User Login Validation
    @GetMapping("/userlogin")
    public String userLogin(@ModelAttribute("userLogin") UserLogin login, Model model) {
        email = login.getUserEmail();
        String password = login.getUserPassword();

        if (userServices.validateLoginCredentials(email, password)) {
            user = this.userServices.getUserByEmail(email);
            List<Orders> orders = this.orderServices.getOrdersForUser(user);
            model.addAttribute("orders", orders);
            model.addAttribute("name", user.getUname());
            return "BuyProduct";
        } else {
            model.addAttribute("error2", "Invalid email or password");
            return "Login";
        }
    }

    // 🟩 Search Product by Name
    @PostMapping("/product/search")
    public String searchHandler(@RequestParam("productName") String name, Model model) {
        Product product = this.productServices.getProductByName(name);
        if (product == null) {
            model.addAttribute("message", "SORRY...! Product Unavailable");
        }
        List<Orders> orders = this.orderServices.getOrdersForUser(user);
        model.addAttribute("orders", orders);
        model.addAttribute("product", product);
        return "BuyProduct";
    }

    // 🟩 Admin Dashboard (All Services)
    @GetMapping("/admin/services")
    public String returnBack(Model model) {
        List<User> users = this.userServices.getAllUsers();
        List<Admin> admins = this.adminServices.getAll();
        List<Product> products = this.productServices.getAllProducts();
        List<Orders> orders = this.orderServices.getOrders();

        model.addAttribute("users", users);
        model.addAttribute("admins", admins);
        model.addAttribute("products", products);
        model.addAttribute("orders", orders);

        return "Admin_Page";
    }

    // 🟩 Add Admin
    @GetMapping("/addAdmin")
    public String addAdminPage() {
        return "Add_Admin";
    }

    @PostMapping("/addingAdmin")
    public String addAdmin(@ModelAttribute Admin admin) {
        this.adminServices.addAdmin(admin);
        return "redirect:/admin/services";
    }

    // 🟩 Update Admin
    @GetMapping("/updateAdmin/{adminId}")
    public String updateAdminPage(@PathVariable("adminId") String id, Model model) {
        Admin admin = this.adminServices.getAdmin(id);
        model.addAttribute("admin", admin);
        return "Update_Admin";
    }

    @PostMapping("/updatingAdmin/{id}")
    public String updateAdmin(@ModelAttribute Admin admin, @PathVariable("id") String id) {
        this.adminServices.update(admin, id);
        return "redirect:/admin/services";
    }

    // 🟩 Delete Admin
    @GetMapping("/deleteAdmin/{id}")
    public String deleteAdmin(@PathVariable("id") String id) {
        this.adminServices.delete(id);
        return "redirect:/admin/services";
    }

    // 🟩 Add Product
    @GetMapping("/addProduct")
    public String addProductPage() {
        return "Add_Product";
    }

    // 🟩 Update Product
    @GetMapping("/updateProduct/{productId}")
    public String updateProductPage(@PathVariable("productId") String id, Model model) {
        Product product = this.productServices.getProduct(id);
        model.addAttribute("product", product);
        return "Update_Product";
    }

    // 🟩 Add User
    @GetMapping("/addUser")
    public String addUserPage() {
        return "Add_User";
    }

    // 🟩 Update User
    @GetMapping("/updateUser/{userId}")
    public String updateUserPage(@PathVariable("userId") String id, Model model) {
        User user = this.userServices.getUser(id);
        model.addAttribute("user", user);
        return "Update_User";
    }

    // 🟩 Place Order
    @PostMapping("/product/order")
    public String orderHandler(@ModelAttribute Orders order, Model model) {
        double totalAmount = Logic.countTotal(order.getoPrice(), order.getoQuantity());
        order.setTotalAmount(totalAmount);
        order.setUser(user);
        order.setOrderDate(new Date());

        this.orderServices.saveOrder(order);
        model.addAttribute("amount", totalAmount);

        return "Order_success";
    }

    // 🟩 Back to BuyProduct Page
    @GetMapping("/product/back")
    public String back(Model model) {
        List<Orders> orders = this.orderServices.getOrdersForUser(user);
        model.addAttribute("orders", orders);
        return "BuyProduct";
    }
}
