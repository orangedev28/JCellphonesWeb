package com.example.demo.validators;

import com.example.demo.repository.IUserRepository;
import com.example.demo.validators.annotation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RequiredArgsConstructor
public class ValidUsernameValidator implements
        ConstraintValidator<ValidUsername, String> {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext
            context) {
        if(userRepository == null)
            return true;
        return userRepository.findByUsername(username) == null;
    }
}