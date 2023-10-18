package com.example.demo.services;

import com.example.demo.entity.Category;
import com.example.demo.repository.ICategoryRepository;
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
public class CategoryService {
    private final ICategoryRepository categoryRepository;
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
    public void updateCategory(@NotNull Category category) {
        Category existingCategory = categoryRepository.findById(category.getId())
                .orElse(null);
        Objects.requireNonNull(existingCategory)
                .setName(category.getName());
        existingCategory.setUpdatedBy(category.getUpdatedBy());
        existingCategory.setUpdatedDate(category.getUpdatedDate());
        existingCategory.setStatus(category.isStatus());
        categoryRepository.save(existingCategory);
    }
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
