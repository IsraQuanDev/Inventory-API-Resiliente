package com.inventory.repository; 

import com.inventory.entity.Product;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@RequiredArgsConstructor
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryIgnoreCase(String category);

    List<Product> findByStockGreaterThan(Integer stock);
}