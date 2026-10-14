package com.ecommerce.productcatalogservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product extends BaseEntity{

    @Column(unique = true)
    private String name;
    @Column(precision = 20, scale = 3)
    private BigDecimal price;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category category;
}
