package com.atharva.inventorymanagement.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.atharva.inventorymanagement.entities.Supplier;
import com.atharva.inventorymanagement.services.SupplierService;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping()
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        ResponseEntity<List<Supplier>> response = new ResponseEntity<List<Supplier>>(suppliers, HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<Supplier> createNewSupplier(@RequestBody @Valid Supplier supplier) {
        Supplier newSupplier = supplierService.createNewSupplier(supplier);
        return new ResponseEntity<>(newSupplier, HttpStatus.CREATED);
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<Supplier> getProductById(@PathVariable Long supplierId) {
        Optional<Supplier> supplier = supplierService.getSupplierById(supplierId);
        if (supplier.isPresent()) {
            ResponseEntity<Supplier> response = new ResponseEntity<Supplier>(supplier.get(), null, HttpStatus.OK);
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "this item was not found!");
        }
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Supplier> updateProductById(@PathVariable Long supplierId,
            @RequestBody @Valid Supplier updatedSupplier) {
        Optional<Supplier> supplier = supplierService.updateSupplierbyId(supplierId, updatedSupplier);
        if (supplier.isPresent()) {
            ResponseEntity<Supplier> response = new ResponseEntity<Supplier>(supplier.get(), null, HttpStatus.OK);
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error while attempting to update product!");
        }
    }

    @DeleteMapping("/{supplierId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long supplierId) {
        Boolean result = supplierService.deleteSupplierById(supplierId);
        if (result == true) {
            ResponseEntity<String> response = new ResponseEntity<String>("product deleted successfully", null,
                    HttpStatus.OK);
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error while attempting to delete product!");
        }
    }
}
