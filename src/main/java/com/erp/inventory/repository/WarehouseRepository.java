package com.erp.inventory.repository;

import com.erp.inventory.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  WarehouseRepository extends JpaRepository<Warehouse, Long> {
    public Optional<Warehouse> findByName(String warehouseName);
}
