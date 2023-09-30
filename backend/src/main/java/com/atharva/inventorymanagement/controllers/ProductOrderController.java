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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atharva.inventorymanagement.entities.ProductOrder;
import com.atharva.inventorymanagement.entities.Product;
import com.atharva.inventorymanagement.entities.Stock;
import com.atharva.inventorymanagement.entities.Supplier;
import com.atharva.inventorymanagement.services.ProductOrderService;
import com.atharva.inventorymanagement.services.ProductService;
import com.atharva.inventorymanagement.services.StockService;
import com.atharva.inventorymanagement.services.SupplierService;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/productOrders")
public class ProductOrderController {

    @Autowired
    ProductOrderService productOrderService;

    @Autowired
    ProductService productService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    StockService stockService;

    // get all stocks
    @GetMapping()
    public ResponseEntity<List<ProductOrder>> getAllOrders() {
        List<ProductOrder> orders = productOrderService.getAllOrders();
        ResponseEntity<List<ProductOrder>> response = new ResponseEntity<List<ProductOrder>>(orders, HttpStatus.OK);
        return response;
    }

    // create new stock
    @PostMapping()
    public ResponseEntity<?> createNewOrder(@RequestBody @Valid ProductOrder order) {
        Optional<Product> product = productService.getProductById(order.getProduct().getId());
        Optional<Supplier> supplier = supplierService.getSupplierById(order.getSupplier().getId());

        if (product.isPresent() && supplier.isPresent()) {

            // create new order
            ProductOrder newOrder = new ProductOrder();
            newOrder.setProduct(product.get());
            newOrder.setSupplier(supplier.get());
            newOrder.setQuantity(order.getQuantity());
            newOrder = productOrderService.createNewOrder(newOrder);

            // check if the stock for given product exists
            List<Stock> stock = stockService.getStockByProductId(product.get().getId());

            if (stock.size() > 0) {
                Stock currentStock = stock.get(0);
                Integer currentQuantity = currentStock.getQuantity();
                Integer updatedQuantity = currentQuantity + order.getQuantity();
                currentStock.setQuantity(updatedQuantity);
                stockService.updateStock(currentStock);
            } else {
                Stock newStock = new Stock();
                newStock.setProduct(product.get());
                newStock.setQuantity(order.getQuantity());
                newStock = stockService.createNewStock(newStock);
            }

            return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
        }
        
        return new ResponseEntity<>("invalid product order!", HttpStatus.CREATED);
    }

    // get stock ny id
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        List<Stock> stocks = stockService.getStockByProductId(productId);
        if (stocks.size() > 0) {
            return new ResponseEntity<Stock>(stocks.get(0), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("invalid product!", HttpStatus.CREATED);
        }
    }
}
