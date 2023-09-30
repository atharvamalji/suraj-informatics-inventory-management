package com.atharva.inventorymanagement.controllers;

import java.util.List;

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
}
