package com.erp.inventory.repository;

import com.erp.inventory.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
