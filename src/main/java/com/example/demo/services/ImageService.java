package com.example.demo.services;

import com.example.demo.entity.Images;
import com.example.demo.repository.IImagesRepository;
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
public class ImageService {
    private final IImagesRepository iImagesRepository;
    public List<Images> getAllImages() {
        return iImagesRepository.findAll();
    }
    public Optional<Images> getImageById(Long id) {
        return iImagesRepository.findById(id);
    }
    public void addImage(Images images) {
        iImagesRepository.save(images);
    }
    public void updateImage(@NotNull Images images) {
        Images existingImage = iImagesRepository.findById(images.getId())
                .orElse(null);
        Objects.requireNonNull(existingImage)
                .setImage(images.getImage());
        iImagesRepository.save(existingImage);
    }
    public void deleteImageById(Long id) {
        iImagesRepository.deleteById(id);
    }
}
