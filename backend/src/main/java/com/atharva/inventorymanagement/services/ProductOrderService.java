package com.atharva.inventorymanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atharva.inventorymanagement.entities.ProductOrder;
import com.atharva.inventorymanagement.repositories.ProductOrderRepository;

@Service
public class ProductOrderService {

    @Autowired
    ProductOrderRepository productOrderRepository;

    public List<ProductOrder> getAllOrders() {
        List<ProductOrder> result = new ArrayList<ProductOrder>();
        Iterable<ProductOrder> products = productOrderRepository.findAll();
        products.forEach(result::add);
        return result;
    }

    public ProductOrder createNewOrder(ProductOrder productOrder) {
        ProductOrder result = productOrderRepository.save(productOrder);
        return result;
    }
}
