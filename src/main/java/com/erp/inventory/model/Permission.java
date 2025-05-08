package com.erp.inventory.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name; // e.g., "INVENTORY_READ", "ORDER_APPROVE"

    private String description;
}