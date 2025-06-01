package com.erp.inventory.controller;

import com.erp.inventory.dto.WarehouseRequestDTO;
import com.erp.inventory.dto.WarehouseResponseDTO;
import com.erp.inventory.service.WarehouseService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@AllArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<WarehouseResponseDTO> createWarehouse(@RequestBody WarehouseRequestDTO warehouseRequestDTO) {
        return ResponseEntity.ok(warehouseService.createWarehouse(warehouseRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponseDTO> getWarehouseById(@PathVariable Long id) {
        return ResponseEntity.ok(warehouseService.getWarehouseById(id));
    }

    @GetMapping
    public ResponseEntity<List<WarehouseResponseDTO>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

    @PutMapping
    public ResponseEntity<WarehouseResponseDTO> updateWarehouse(@RequestBody WarehouseRequestDTO warehouseRequestDTO) {
        return ResponseEntity.ok(warehouseService.updateWarehouse(warehouseRequestDTO));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable @NotBlank String name) {
        warehouseService.deleteWarehouse(name);
        return ResponseEntity.noContent().build();
    }
}
