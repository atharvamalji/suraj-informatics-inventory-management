package com.atharva.inventorymanagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atharva.inventorymanagement.entities.Supplier;
import com.atharva.inventorymanagement.repositories.SupplierRepository;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        List<Supplier> result = new ArrayList<Supplier>();
        Iterable<Supplier> supplier = supplierRepository.findAll();
        supplier.forEach(result::add);
        return result;
    }

    public Supplier createNewSupplier(Supplier supplier) {
        Supplier result = supplierRepository.save(supplier);
        return result;
    }

    public Optional<Supplier> getSupplierById(Long supplierId) {
        Optional<Supplier> result = supplierRepository.findById(supplierId);
        return result;
    }

    public Optional<Supplier> updateSupplierbyId(Long supplierId, Supplier updatedSupplier) {
        Optional<Supplier> existingSupplierOptional = supplierRepository.findById(supplierId);
        if (existingSupplierOptional.isPresent()) {
            Supplier existingSupplier = existingSupplierOptional.get();
            existingSupplier.setName(updatedSupplier.getName());
            existingSupplier.setLocation(updatedSupplier.getLocation());
            existingSupplier.setPhoneNumber(updatedSupplier.getPhoneNumber());

            Supplier updated = supplierRepository.save(existingSupplier);
            return Optional.of(updated);
        } else {
            return Optional.empty();
        }
    }

    public Boolean deleteSupplierById(Long supplierId) {
        if (supplierRepository.existsById(supplierId) == true) {
            supplierRepository.deleteById(supplierId);
            return true;
        } else {
            return false;
        }
    }
}
