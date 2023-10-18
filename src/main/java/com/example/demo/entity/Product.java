package com.example.demo.entity;

import com.example.demo.validators.annotation.ValidBrandId;
import com.example.demo.validators.annotation.ValidCategoryId;
import com.example.demo.validators.annotation.ValidSupplierId;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 50, nullable = false)
    @Size(min = 1, max = 50, message = "Title must be between 1 and 50 characters")
    @NotBlank(message = "Title must not be blank")
    private String name;

    @Column(name = "status")
    private boolean status;
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    @Positive(message = "Price must be greater than 0")
    private Long price;
    @Column(name = "discount")
    private int discount;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "decription")
    private String decription;
    @Column(name = "detail")
    private String detail;
    @Column(name = "color")
    private String color;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "createdBy")
    private Long createdBy;
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "updatedBy")
    private Long updatedBy;
    @Column(name = "updatedDate")
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ValidCategoryId
    @ToString.Exclude
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @ValidBrandId
    @ToString.Exclude
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ValidSupplierId
    @ToString.Exclude
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Images> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<OrderDetail> orderDetails = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) !=
                Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(),
                product.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
