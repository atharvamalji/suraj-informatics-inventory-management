package com.atharva.inventorymanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name cannot be empty!")
    @Column(nullable = false, length = 64)
    private String name;

    @NotBlank(message = "location cannot be empty!")
    @Column(nullable = false, length = 64)
    private String location;

    @NotBlank(message = "phone number cannot be empty!")
    @Pattern(regexp="(^$|[0-9]{10})", message = "invalid phone number!")
    @Column(nullable = false)
    private String phoneNumber;
}
