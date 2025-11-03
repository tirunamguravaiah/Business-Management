package com.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.business.entities.Product;
import com.business.services.ProductServices;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    // 🟩 Add Product
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        this.productServices.addProduct(product);
        return "redirect:/admin/services";
    }

    // 🟩 Show Update Product Page
    @GetMapping("/edit/{productId}")
    public String editProduct(@PathVariable("productId") String id, Model model) {
        Product product = this.productServices.getProduct(id);
        model.addAttribute("product", product);
        return "Update_Product";
    }

    // 🟩 Update Product
    @PostMapping("/update/{productId}")
    public String updateProduct(@ModelAttribute Product product, @PathVariable("productId") String id) {
        this.productServices.updateProduct(product, id);
        return "redirect:/admin/services";
    }

    // 🟩 Delete Product
    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") String id) {
        this.productServices.deleteProduct(id);
        return "redirect:/admin/services";
    }
}
