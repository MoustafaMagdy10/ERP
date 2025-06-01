package com.erp.inventory.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    private Integer quantity;

    private LocalDateTime lastUpdated;
}
