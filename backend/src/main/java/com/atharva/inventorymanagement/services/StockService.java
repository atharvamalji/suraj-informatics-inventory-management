package com.atharva.inventorymanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atharva.inventorymanagement.entities.Stock;
import com.atharva.inventorymanagement.repositories.StockRepository;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        List<Stock> result = new ArrayList<Stock>();
        Iterable<Stock> supplier = stockRepository.findAll();
        supplier.forEach(result::add);
        return result;
    }

    public Stock createNewStock(Stock stock) {
        Stock result = stockRepository.save(stock);
        return result;
    }

    public Stock updateStock(Stock updatedStock) {
        Stock result = stockRepository.save(updatedStock);
        return result;
    }

    public Optional<Stock> getStockById(Long stockId) {
        Optional<Stock> result = stockRepository.findById(stockId);
        return result;
    }

    public List<Stock> getStockByProductId(Long productId) {
        List<Stock> result = stockRepository.findByProductId(productId);
        return result;
    }
}
