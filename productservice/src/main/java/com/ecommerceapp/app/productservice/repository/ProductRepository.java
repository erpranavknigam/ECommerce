package com.ecommerceapp.app.productservice.repository;

import com.ecommerceapp.app.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
