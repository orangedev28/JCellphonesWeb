package com.example.demo.controller;

import com.example.demo.daos.Item;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.services.ProductService;
import com.example.demo.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductService productService;
    private List<Product> product;

    @GetMapping
    public String showAllBooks(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "home/index";
    }
    @GetMapping("/contact")
    public String contact(){return "home/contact";}
    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
                            @RequestParam long id,
                            @RequestParam String name,
                            @RequestParam String image,
                            @RequestParam double price,
                            @RequestParam(defaultValue = "1") int quantity)
    {

        var cart = CartService.getCart(session);
        cart.addItems(new Item(id, name, image , price, quantity));
        CartService.updateCart(session, cart);
        return "redirect:/";
    }
}