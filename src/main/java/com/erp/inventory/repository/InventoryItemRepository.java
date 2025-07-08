package com.erp.inventory.repository;

import com.erp.inventory.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
