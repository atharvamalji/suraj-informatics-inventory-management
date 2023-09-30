package com.atharva.inventorymanagement.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atharva.inventorymanagement.entities.Product;
import com.atharva.inventorymanagement.entities.Stock;
import com.atharva.inventorymanagement.services.ProductService;
import com.atharva.inventorymanagement.services.StockService;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    StockService stockService;

    @Autowired
    ProductService productService;

    // get all stocks
    @GetMapping()
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> suppliers = stockService.getAllStocks();
        ResponseEntity<List<Stock>> response = new ResponseEntity<List<Stock>>(suppliers, HttpStatus.OK);
        return response;
    }

    // create new stock
    @PostMapping()
    public ResponseEntity<?> createNewStock(@RequestBody @Valid Stock stock) {
        Optional<Product> product = productService.getProductById(stock.getProduct().getId());
        if (product.isPresent()) {
            Stock newStock = new Stock();
            newStock.setProduct(product.get());
            newStock.setQuantity(stock.getQuantity());
            newStock = stockService.createNewStock(newStock);
            return new ResponseEntity<>(newStock, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("invalid product!", HttpStatus.CREATED);
    }

}
