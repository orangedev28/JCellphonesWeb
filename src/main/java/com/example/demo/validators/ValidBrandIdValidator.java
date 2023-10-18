package com.example.demo.validators;

import com.example.demo.entity.Brand;

import com.example.demo.validators.annotation.ValidBrandId;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidBrandIdValidator implements ConstraintValidator<ValidBrandId, Brand> {
    @Override
    public boolean isValid(Brand brand, ConstraintValidatorContext context) {
        return brand != null && brand.getId() != null;
    }
}
