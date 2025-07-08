package com.erp.inventory.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryItemResponseDTO {
    private Long id;
    private String productName;
    private String warehouseName;
    private int quantity;
    private LocalDateTime lastUpdated;
}