package com.inventory.service;

import com.inventory.dto.*;
import com.inventory.entity.Product;
import com.inventory.exception.ProductNotFoundException;
import com.inventory.repository.ProductRepository;
 

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse create(ProductRequest request) {
        Product product = new Product();

        product.setName(request.name());
        product.setDescription(request.description());
        product.setCategory(request.category());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return toResponse(repository.save(product));
    }

    public List<ProductResponse> findAll(String category) {
        List<Product> products = category == null
                ? repository.findAll()
                : repository.findByCategoryIgnoreCase(category);

        return products.stream().map(this::toResponse).toList();
    }

    public ProductResponse findById(Long id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getStock()
        );
    }
    
    
   
    public ProductResponse updateStock(Long id, Integer stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setStock(stock);
        product.setUpdatedAt(LocalDateTime.now());

        return toResponse(repository.save(product));
    }

    public void delete(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        repository.delete(product);
    }
    
    
}