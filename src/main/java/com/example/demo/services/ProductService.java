package com.example.demo.services;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.IProductRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final IProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> pagingProduct(Integer pageNo, Integer pageSize, String sortBy) {
        return productRepository.findAllProduct(pageNo, pageSize, sortBy);
    }
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public void updateProduct(@NotNull Product product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElse(null);
        Objects.requireNonNull(existingProduct)
                .setName(product.getName());

        existingProduct.setUpdatedBy(product.getUpdatedBy());
        existingProduct.setUpdatedDate(product.getUpdatedDate());
        existingProduct.setStatus(product.isStatus());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setDecription(product.getDecription());
        existingProduct.setDetail(product.getDetail());
        existingProduct.setImage(product.getImage());
        existingProduct.setCapacity(product.getCapacity());
        existingProduct.setColor(product.getColor());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setStatus(product.isStatus());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setSupplier(product.getSupplier());
        productRepository.save(existingProduct);
    }
    public void deleteProductById(Long id) {productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }
}
