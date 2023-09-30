package com.atharva.inventorymanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atharva.inventorymanagement.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
}
