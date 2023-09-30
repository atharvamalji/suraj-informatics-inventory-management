package com.atharva.inventorymanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "sku cannot be empty")
    @Column(nullable = false, unique = true, length = 64)
    private String sku;

    @NotBlank(message = "name cannot be empty")
    @Column(nullable = false, length = 64)
    private String name;

    @NotBlank(message = "description cannot be empty")
    @Column(nullable = false, length = 1024)
    private String description;

    @NotNull(message = "positive number value is required")
    @Column(nullable = false)
    private Float price;

    @NotBlank(message = "unit cannot be empty")
    @Column(nullable = false, length = 32)
    private String unit;
}
