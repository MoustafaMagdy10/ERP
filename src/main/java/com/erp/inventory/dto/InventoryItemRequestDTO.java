package com.erp.inventory.dto;

import lombok.Data;

@Data
public class InventoryItemRequestDTO {
    private String productName;
    private String warehouseName;
    private int quantity;
}
