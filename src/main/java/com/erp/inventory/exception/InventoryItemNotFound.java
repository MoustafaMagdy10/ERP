package com.erp.inventory.exception;

public class InventoryItemNotFound extends RuntimeException {
    public InventoryItemNotFound(String message) {
        super(message);
    }
}
