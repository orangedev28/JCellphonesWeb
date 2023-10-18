package com.example.demo.repository;

import com.example.demo.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagesRepository extends JpaRepository<Images,Long> {
}
