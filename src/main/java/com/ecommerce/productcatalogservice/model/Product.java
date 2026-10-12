package com.ecommerce.productcatalogservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product extends BaseEntity{

    private String name;
    @Column(precision = 20, scale = 3)
    private BigDecimal price;
    private String description;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
