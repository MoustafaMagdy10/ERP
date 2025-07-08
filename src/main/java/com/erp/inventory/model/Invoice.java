package com.erp.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.erp.inventory.model.enums.*;

@Entity
@Table(name = "Invoice")
@Data

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = true)
    private Supplier supplier;

    @OneToMany(mappedBy = "invoice" , cascade = CascadeType.ALL)
    private List<InvoiceItem> items;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

    private LocalDate dueDate;




}
