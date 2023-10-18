package com.example.demo.controller;

import com.example.demo.entity.Images;
import com.example.demo.entity.Product;
import com.example.demo.services.ImageService;
import com.example.demo.services.ProductService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    private ImageService imageService;
    private List<Product> products;
    private List<Images> images;

    @GetMapping
    public String shop(Model model,@RequestParam(defaultValue = "0") Integer pageNo,
                       @RequestParam(defaultValue = "4") Integer pageSize,
                       @RequestParam(defaultValue = "id") String sortBy ){

        model.addAttribute("products",productService.pagingProduct(pageNo,pageSize, sortBy));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", productService.getAllProducts().size() / pageSize);
        model.addAttribute("category",0);
        return "shop/index";
    }

    @GetMapping("/category/{id}")
    public String category(Model model,@PathVariable long id ){
       products = productService.getAllProducts().stream().filter(u->u.getCategory().getId()==id).toList();
        model.addAttribute("products", products);
        model.addAttribute("category", id);
        return "shop/brand";
    }

    @GetMapping("/brand/{id}")
    public String brand(Model model,@PathVariable long id ){
        products = productService.getAllProducts().stream().filter(u->u.getBrand().getId()==id).toList();
        model.addAttribute("products", products);
        model.addAttribute("category",0);
        return "shop/brand";
    }

    @GetMapping("/product/{id}")
    public String product(Model model,@PathVariable long id){
        products = productService.getAllProducts().stream().filter(u->u.getId()==id).toList();

        model.addAttribute("products", products);
        return "shop/product";
    }

    @GetMapping("/search")
    public String searchProduct(
            @NotNull Model model,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "4") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        model.addAttribute("products", productService.searchProduct(keyword));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",
                productService.getAllProducts().size()/ pageSize);

        model.addAttribute("category",0);
        return "shop/index";
    }
}
