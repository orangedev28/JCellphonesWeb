package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {
    default List<Product> findAllProduct(Integer pageNo,
                                    Integer pageSize,
                                    String sortBy) {
        return findAll(PageRequest.of(pageNo,
                pageSize,
                Sort.by(sortBy)))
                .getContent();
    }
    @Query("""
 SELECT b FROM Product b
 WHERE b.name LIKE %?1%
 OR b.category.name LIKE %?1%
 OR b.brand.name LIKE %?1%
 """)
    List<Product> searchProduct(String keyword);
}