package com.ecommerce.productcatalogservice.repository;

import com.ecommerce.productcatalogservice.model.Product;
import com.ecommerce.productcatalogservice.model.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByIdAndState(Long id, State state);
}
