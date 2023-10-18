package com.example.demo.services;


import com.example.demo.entity.Brand;
import com.example.demo.repository.IBrandRepository;
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
public class BrandService {
    private final IBrandRepository brandRepository;
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }
    public void addBrand(Brand brand) {
        brandRepository.save(brand);
    }
    public void updateBrand(@NotNull Brand brand) {
        Brand existingBrand = brandRepository.findById(brand.getId())
                .orElse(null);
        Objects.requireNonNull(existingBrand)
                .setName(brand.getName());
        existingBrand.setUpdatedBy(brand.getUpdatedBy());
        existingBrand.setUpdatedDate(brand.getUpdatedDate());
        existingBrand.setStatus(brand.isStatus());
        brandRepository.save(existingBrand);
    }
    public void deleteBrandById(Long id) {
        brandRepository.deleteById(id);
    }
}
