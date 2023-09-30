package com.atharva.inventorymanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atharva.inventorymanagement.entities.ProductOrder;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long>{
    
}
