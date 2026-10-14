package com.ecommerce.productcatalogservice.model;

import com.ecommerce.productcatalogservice.model.enums.State;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private State state;
    private Date createdAt;
    private Date lastUpdatedAt;
}
