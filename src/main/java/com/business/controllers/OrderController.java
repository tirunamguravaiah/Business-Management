package com.business.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.business.entities.Orders;
import com.business.entities.Product;
import com.business.entities.User;
import com.business.services.OrderServices;
import com.business.services.ProductServices;
import com.business.services.UserServices;
import com.business.basiclogics.Logic;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @Autowired
    private ProductServices productServices;

    @Autowired
    private UserServices userServices;

    // 🟩 Get all orders
    @GetMapping("/all")
    public String getAllOrders(Model model) {
        List<Orders> orders = orderServices.getOrders();
        model.addAttribute("orders", orders);
        return "All_Orders"; // View name for all orders page
    }

    // 🟩 Get orders for a specific user
    @GetMapping("/user/{userId}")
    public String getUserOrders(@PathVariable("userId") String userId, Model model) {
        User user = userServices.getUser(userId);
        List<Orders> userOrders = orderServices.getOrdersForUser(user);
        model.addAttribute("orders", userOrders);
        model.addAttribute("user", user);
        return "User_Orders"; // View showing user's order history
    }

    // 🟩 Place a new order (example: through a form)
    @PostMapping("/place")
    public String placeOrder(@ModelAttribute Orders order,
                             @RequestParam("userId") String userId,
                             @RequestParam("productId") String productId,
                             Model model) {
        User user = userServices.getUser(userId);
        Product product = productServices.getProduct(productId);

        order.setUser(user);
        order.setoName(product.getPname());
        order.setoPrice(product.getPprice());
        order.setOrderDate(new Date());
        order.setTotalAmount(Logic.countTotal(order.getoPrice(), order.getoQuantity()));

        orderServices.saveOrder(order);

        model.addAttribute("message", "Order placed successfully!");
        model.addAttribute("order", order);

        return "Order_Success"; // Confirmation view
    }

    // 🟩 Delete an order
    @GetMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String orderId) {
        orderServices.deleteOrder(orderId);
        return "redirect:/orders/all";
    }
}
