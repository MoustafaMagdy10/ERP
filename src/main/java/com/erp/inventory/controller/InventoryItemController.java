package com.erp.inventory.controller;

import com.erp.inventory.dto.InventoryItemRequestDTO;
import com.erp.inventory.dto.InventoryItemResponseDTO;
import com.erp.inventory.service.InventoryItemService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    @PostMapping
    public InventoryItemResponseDTO createInventoryItem(@RequestBody InventoryItemRequestDTO requestDTO) {
        return inventoryItemService.createInventoryItem(requestDTO);
    }

    @GetMapping("/{id}")
    public InventoryItemResponseDTO getInventoryItemById(@PathVariable Long id) {
        return inventoryItemService.getInventoryItemById(id);
    }

    @GetMapping
    public List<InventoryItemResponseDTO> getAllInventoryItems() {
        return inventoryItemService.getAllInventoryItems();
    }

    @PutMapping("/{id}")
    public InventoryItemResponseDTO updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItemRequestDTO requestDTO) {
        return inventoryItemService.updateInventoryItem(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteInventoryItem(@PathVariable Long id) {
        inventoryItemService.deleteInventoryItem(id);
    }
}
