package com.example.demo.services;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Supplier;
import com.example.demo.repository.IBrandRepository;
import com.example.demo.repository.ISupplierRepository;
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
public class SupplierService {
    private final ISupplierRepository supplierRepository;
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }
    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }
    public void updateSupplier(@NotNull Supplier supplier) {
        Supplier existingSupplier = supplierRepository.findById(supplier.getId())
                .orElse(null);
        Objects.requireNonNull(existingSupplier)
                .setName(supplier.getName());
        existingSupplier.setUpdatedBy(supplier.getUpdatedBy());
        existingSupplier.setUpdatedDate(supplier.getUpdatedDate());
        existingSupplier.setAddress(supplier.getAddress());
        existingSupplier.setEmail(supplier.getEmail());
        existingSupplier.setPhone(supplier.getPhone());
        existingSupplier.setStatus(supplier.isStatus());
        supplierRepository.save(existingSupplier);
    }
    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }
//    public List<Supplier> getAllSuppliers() {
//        return supplierRepository.findAll();
//    }
//    public Optional<Supplier> getSupplierById(Long id) {
//        return supplierRepository.findById(id);
//    }
//    public void addSupplier(Supplier supplier) {
//        supplierRepository.save(supplier);
//    }
//    public void updateSupplier(@NotNull Supplier supplier) {
//        Supplier existingSupplier = supplierRepository.findById(supplier.getId())
//                .orElse(null);
//        Objects.requireNonNull(existingSupplier)
//                .setName(supplier.getName());
//        existingSupplier.setUpdatedBy(supplier.getUpdatedBy());
//        existingSupplier.setUpdatedDate(supplier.getUpdatedDate());
//        existingSupplier.setAddress(supplier.getAddress());
//        existingSupplier.setEmail(supplier.getEmail());
//        existingSupplier.setPhone(supplier.getPhone());
//        existingSupplier.setStatus(supplier.isStatus());
//        supplierRepository.save(existingSupplier);
//    }
//    public void deleteSupplierById(Long id) {
//        supplierRepository.deleteById(id);
//    }
}
