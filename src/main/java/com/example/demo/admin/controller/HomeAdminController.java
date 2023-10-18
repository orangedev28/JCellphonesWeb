package com.example.demo.admin.controller;

import com.example.demo.entity.*;
import com.example.demo.services.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class HomeAdminController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final SupplierService supplierService;
    private final UserService userService;
    private final CartService cartService;
    private final ImageService imageService;
    @GetMapping
    public String homeAdmin() {return "admin/index";}
    ////////////////////////Product//////////////////////////////////////////////////////
    @GetMapping("/product/index")
    public String showAllProduct(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/index";
    }
    @GetMapping("/product/add")
    public String addProductForm(@NotNull Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories",
                categoryService.getAllCategories());
        model.addAttribute("brands",
                brandService.getAllBrands());
        model.addAttribute("suppliers",
                supplierService.getAllSuppliers());
        return "product/add";
    }
    @PostMapping("/product/add")
    public String addProduct(
            @Valid @ModelAttribute("product") Product product,
            @NotNull BindingResult bindingResult,
            Model model,@RequestParam("coverImage") MultipartFile file,@RequestParam("files") MultipartFile[] files)throws IOException {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            return "product/add";
//        }
        if (!file.isEmpty()) {
            File saveFile = new ClassPathResource("static/images/ImagesUpload/").getFile();
            String fileName = UUID.randomUUID()+ "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
            Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
            Files.copy(file.getInputStream(), path);
            product.setImage("/images/ImagesUpload/"+fileName);
        }
        productService.addProduct(product);
        if(files.length>0){
            List<Images> listimage= new ArrayList<>();
            for (MultipartFile item : files) {

                Images images=new Images();

                File saveFile = new ClassPathResource("static/images/ImagesUpload/").getFile();
                String fileName = UUID.randomUUID()+ "." + StringUtils.getFilenameExtension(item.getOriginalFilename());
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
                Files.copy(file.getInputStream(), path);
                images.setImage("/images/ImagesUpload/"+fileName);
                images.setProduct(product);
                imageService.addImage(images);
            }

        }
        return "redirect:/admin/product/index";
    }
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.getProductById(id)
                .ifPresentOrElse(
                        product -> productService.deleteProductById(id),
                        () -> { throw new IllegalArgumentException("Product not found"); });
        return "redirect:/admin/product/index";
    }
    @GetMapping("/product/edit/{id}")
    public String editProductForm(@NotNull Model model, @PathVariable long id)
    {
        var product = productService.getProductById(id);
        model.addAttribute("product", product.orElseThrow(() -> new
                IllegalArgumentException("Product not found")));
        model.addAttribute("categories",
                categoryService.getAllCategories());
        model.addAttribute("brands",
                brandService.getAllBrands());
        model.addAttribute("suppliers",
                supplierService.getAllSuppliers());
        return "product/edit";
    }
    @PostMapping("/product/edit")
    public String editProduct(@Valid @ModelAttribute("product") Product product,
                           @NotNull BindingResult bindingResult,
                           Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            model.addAttribute("categories",
//                    categoryService.getAllCategories());
//            model.addAttribute("brands",
//                    brandService.getAllBrands());
//            model.addAttribute("suppliers",
//                    supplierService.getAllSuppliers());
//            return "product/edit";
//        }
        productService.updateProduct(product);
        return "redirect:/admin/product/index";
    }
    ////////////////////////Category//////////////////////////////////////////////////////
    @GetMapping("/category/index")
    public String showAllCategory(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/index";
    }
    @GetMapping("/category/add")
    public String addCategoryForm(@NotNull Model model) {
        model.addAttribute("categories", new Category());
        return "category/add";
    }
    @PostMapping("/category/add")
    public String addCategory(
            @Valid @ModelAttribute("category") Category category,
            @NotNull BindingResult bindingResult,
            Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            return "product/add";
//        }
        categoryService.addCategory(category);
        return "redirect:/admin/category/index";
    }
    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable long id) {
        categoryService.getCategoryById(id)
                .ifPresentOrElse(
                        category -> categoryService.deleteCategoryById(id),
                        () -> { throw new IllegalArgumentException("Category not found"); });
        return "redirect:/admin/category/index";
    }
    @GetMapping("/category/edit/{id}")
    public String editCategoryForm(@NotNull Model model, @PathVariable long id)
    {
        var category = categoryService.getCategoryById(id);
        model.addAttribute("category", category.orElseThrow(() -> new
                IllegalArgumentException("Category not found")));
        return "category/edit";
    }
    @PostMapping("/category/edit")
    public String editCategory(@Valid @ModelAttribute("category") Category category,
                               @NotNull BindingResult bindingResult,
                               Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            model.addAttribute("categories",
//                    categoryService.getAllCategories());
//            model.addAttribute("brands",
//                    brandService.getAllBrands());
//            model.addAttribute("suppliers",
//                    supplierService.getAllSuppliers());
//            return "product/edit";
//        }
        categoryService.updateCategory(category);
        return "redirect:/admin/category/index";
    }
    ////////////////////////User//////////////////////////////////////////////////////
    @GetMapping("/user/index")
    public String showAllUser(Model model){
        List<User> user = userService.getAllUser();
        model.addAttribute("user", user);
        return "user/index";
    }
    @GetMapping("/user/add")
    public String addUsersForm(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }
    @PostMapping("/user/add")
    public String addUsers(
            @Valid @ModelAttribute("user") User user,
            @NotNull BindingResult bindingResult,
            Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            return "product/add";
//        }
        userService.addUser(user);
        return "redirect:/admin/user/index";
    }
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.getUserById(id)
                .ifPresentOrElse(
                        user -> userService.deleteUserById(id),
                        () -> { throw new IllegalArgumentException("User not found"); });
        return "redirect:/admin/user/index";
    }
    @GetMapping("/user/edit/{id}")
    public String editUserForm(@NotNull Model model, @PathVariable long id)
    {
        var user = userService.getUserById(id);
        model.addAttribute("user", user.orElseThrow(() -> new
                IllegalArgumentException("User not found")));
        return "user/edit";
    }
    @PostMapping("/user/edit")
    public String editUser(@Valid @ModelAttribute("user") User user,
                            @NotNull BindingResult bindingResult,
                            Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            model.addAttribute("categories",
//                    categoryService.getAllCategories());
//            model.addAttribute("brands",
//                    brandService.getAllBrands());
//            model.addAttribute("suppliers",
//                    supplierService.getAllSuppliers());
//            return "product/edit";
//        }
        userService.updateUser(user);
        return "redirect:/admin/user/index";
    }
    ////////////////////////Orders//////////////////////////////////////////////////////
    @GetMapping("/orders/index")
    public String showAllOrders(Model model){
        List<Orders> orders = cartService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders/index";
    }
    ////////////////////////Supplier//////////////////////////////////////////////////////
    @GetMapping("/supplier/index")
    public String showAllSupplier(Model model){
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        model.addAttribute("supplier", suppliers);
        return "supplier/index";
    }
    @GetMapping("/supplier/add")
    public String addsupplierForm(@NotNull Model model) {
        model.addAttribute("suppliers", new Supplier());
        return "supplier/add";
    }
    @PostMapping("/supplier/add")
    public String addSupplier(
            @Valid @ModelAttribute("suppliers") Supplier supplier,
            @NotNull BindingResult bindingResult,
            Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            return "product/add";
//        }
        supplierService.addSupplier(supplier);
        return "redirect:/admin/supplier/index";
    }
    @GetMapping("/supplier/delete/{id}")
    public String deleteSupplier(@PathVariable long id) {
        supplierService.getSupplierById(id)
                .ifPresentOrElse(
                        supplier -> supplierService.deleteSupplierById(id),
                        () -> { throw new IllegalArgumentException("Supplier not found"); });
        return "redirect:/admin/supplier/index";
    }
    @GetMapping("/supplier/edit/{id}")
    public String editSupplierForm(@NotNull Model model, @PathVariable long id)
    {
        var supplier = supplierService.getSupplierById(id);
        model.addAttribute("supplier", supplier.orElseThrow(() -> new
                IllegalArgumentException("Supplier not found")));
        return "supplier/edit";
    }
    @PostMapping("/supplier/edit")
    public String editSupplier(@Valid @ModelAttribute("supplier") Supplier supplier,
                            @NotNull BindingResult bindingResult,
                            Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            model.addAttribute("categories",
//                    categoryService.getAllCategories());
//            model.addAttribute("brands",
//                    brandService.getAllBrands());
//            model.addAttribute("suppliers",
//                    supplierService.getAllSuppliers());
//            return "product/edit";
//        }
        supplierService.updateSupplier(supplier);
        return "redirect:/admin/supplier/index";
    }
    ////////////////////////Brand//////////////////////////////////////////////////////
    @GetMapping("/brand/index")
    public String showAllBrand(Model model){
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("branks", brands);
        return "brand/index";
    }
    @GetMapping("/brand/add")
    public String addBrandForm(@NotNull Model model) {
        model.addAttribute("brands", new Brand());
        return "brand/add";
    }
    @PostMapping("/brand/add")
    public String addBrand(
            @Valid @ModelAttribute("brand") Brand brand,
            @NotNull BindingResult bindingResult,
            Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            return "product/add";
//        }
        brandService.addBrand(brand);
        return "redirect:/admin/brand/index";
    }
    @GetMapping("/brand/delete/{id}")
    public String deleteBrand(@PathVariable long id) {
        brandService.getBrandById(id)
                .ifPresentOrElse(
                        brand -> brandService.deleteBrandById(id),
                        () -> { throw new IllegalArgumentException("Brand not found"); });
        return "redirect:/admin/brand/index";
    }
    @GetMapping("/brand/edit/{id}")
    public String editBrandForm(@NotNull Model model, @PathVariable long id)
    {
        var brand = brandService.getBrandById(id);
        model.addAttribute("brand", brand.orElseThrow(() -> new
                IllegalArgumentException("Brand not found")));
        return "brand/edit";
    }
    @PostMapping("/brand/edit")
    public String editBrand(@Valid @ModelAttribute("brand") Brand brand,
                            @NotNull BindingResult bindingResult,
                            Model model) {
//        if (bindingResult.hasErrors()) {
//            var errors = bindingResult.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                    .toArray(String[]::new);
//            model.addAttribute("errors", errors);
//            model.addAttribute("categories",
//                    categoryService.getAllCategories());
//            model.addAttribute("brands",
//                    brandService.getAllBrands());
//            model.addAttribute("suppliers",
//                    supplierService.getAllSuppliers());
//            return "product/edit";
//        }
        brandService.updateBrand(brand);
        return "redirect:/admin/brand/index";
    }
}