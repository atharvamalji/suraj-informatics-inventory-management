package com.atharva.inventorymanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atharva.inventorymanagement.entities.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

}
