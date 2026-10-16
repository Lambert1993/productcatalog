package com.ecommerce.productcatalogservice.repository;

import com.ecommerce.productcatalogservice.model.Product;
import com.ecommerce.productcatalogservice.model.enums.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByIdAndState(Long id, State state);

    //@Query("SELECT p FROM Product p JOIN FETCH p.category c")
    @EntityGraph(attributePaths = {"category"})
    List<Product> findAllBy();

    @EntityGraph(attributePaths = {"category"})
    Page<Product> findByPriceBetweenAndState(BigDecimal minPrice, BigDecimal maxPrice, State state, Pageable pageable);


}
