package com.erp.inventory.controller;

import com.erp.inventory.dto.InvoiceDTO;
import com.erp.inventory.model.Invoice;
import com.erp.inventory.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceDTO dto) {
        Invoice created = invoiceService.createInvoice(dto);
        return ResponseEntity.ok(created);
    }
}
