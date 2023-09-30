package com.atharva.inventorymanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atharva.inventorymanagement.entities.Product;
import com.atharva.inventorymanagement.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<Product>();
        Iterable<Product> products = productRepository.findAll();
        products.forEach(result::add);
        return result;
    }

    public Product createProduct(Product product) {
        Product result = productRepository.save(product);
        return result;
    }

    public Optional<Product> getProductById(Long productId) {
        Optional<Product> result = productRepository.findById(productId);
        return result;
    }

    public Optional<Product> updateProductbyId(Long productId, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setSku(updatedProduct.getSku());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setUnit(updatedProduct.getUnit());

            Product updated = productRepository.save(existingProduct);
            return Optional.of(updated);
        } else {
            return Optional.empty();
        }
    }

    public Boolean deleteProductById(Long productId) {
        if (productRepository.existsById(productId) == true) {
            productRepository.deleteById(productId);
            return true;
        } else {
            return false;
        }
    }
}
