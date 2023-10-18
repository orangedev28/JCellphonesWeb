package com.example.demo.validators;


import com.example.demo.entity.Supplier;
import com.example.demo.validators.annotation.ValidSupplierId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidSupplierIdValidator implements ConstraintValidator<ValidSupplierId, Supplier> {
    @Override
    public boolean isValid(Supplier supplier, ConstraintValidatorContext context) {
        return supplier != null && supplier.getId() != null;
    }
}