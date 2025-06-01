package com.erp.inventory.exception;

public class WarehouseNotFound extends RuntimeException {
    public WarehouseNotFound(String message) {
        super(message);
    }
}
