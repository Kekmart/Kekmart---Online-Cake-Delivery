package com.kekmart.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kekmart.application.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
