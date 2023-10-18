package com.example.demo.validators;


import com.example.demo.entity.Category;
import com.example.demo.validators.annotation.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category productCategory, ConstraintValidatorContext context) {
        return productCategory != null && productCategory.getId() != null;
    }
}
