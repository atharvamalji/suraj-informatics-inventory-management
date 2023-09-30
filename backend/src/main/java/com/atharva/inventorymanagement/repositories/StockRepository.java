package com.atharva.inventorymanagement.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.atharva.inventorymanagement.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    
    List<Stock> findByProductId(Long productId);
}
