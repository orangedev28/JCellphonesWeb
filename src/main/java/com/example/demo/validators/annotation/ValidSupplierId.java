package com.example.demo.validators.annotation;


import com.example.demo.validators.ValidSupplierIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidSupplierIdValidator.class)
@Documented
public @interface ValidSupplierId {

    String message() default "Invalid Brand ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}