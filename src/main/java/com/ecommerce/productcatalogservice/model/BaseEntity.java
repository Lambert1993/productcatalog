package com.ecommerce.productcatalogservice.model;

import com.ecommerce.productcatalogservice.model.enums.State;

import java.util.Date;

public abstract class BaseEntity {

    private int id;
    private State state;
    private Date createdAt;
    private Date lastUpdatedAt;
}
