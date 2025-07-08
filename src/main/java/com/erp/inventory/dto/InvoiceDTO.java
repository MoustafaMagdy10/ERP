package com.erp.inventory.dto;

import com.erp.inventory.model.enums.InvoiceType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InvoiceDTO {
    private InvoiceType invoiceType;
    private Long clientId;
    private Long supplierId;
    private LocalDate dueDate;
    private List<InvoiceItemDTO> items;

}
