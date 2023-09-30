package com.atharva.inventorymanagement.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.atharva.inventorymanagement.entities.Product;
import com.atharva.inventorymanagement.services.ProductService;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ResponseEntity<List<Product>> response = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            ResponseEntity<Product> response = new ResponseEntity<Product>(product.get(), null, HttpStatus.OK);
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "this item was not found!");
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long productId, @RequestBody @Valid Product updatedProduct) {
        Optional<Product> product = productService.updateProductbyId(productId, updatedProduct);
        if (product.isPresent()) {
            ResponseEntity<Product> response = new ResponseEntity<Product>(product.get(), null, HttpStatus.OK);
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error while attempting to update product!");
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId) {
        Boolean result = productService.deleteProductById(productId);
        if (result == true) {
            ResponseEntity<String> response = new ResponseEntity<String>("product deleted successfully", null, HttpStatus.OK);
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error while attempting to delete product!");
        }
    }

}
