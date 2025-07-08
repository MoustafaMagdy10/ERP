package com.erp.inventory.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceItemDTO {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;

}
