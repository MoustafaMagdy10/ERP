package com.erp.inventory.service;

import com.erp.inventory.dto.InventoryItemRequestDTO;
import com.erp.inventory.dto.InventoryItemResponseDTO;
import com.erp.inventory.exception.InventoryItemNotFound;
import com.erp.inventory.model.InventoryItem;
import com.erp.inventory.model.Product;
import com.erp.inventory.model.Warehouse;
import com.erp.inventory.repository.InventoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;
    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final ModelMapper modelMapper;

    public InventoryItemResponseDTO createInventoryItem(InventoryItemRequestDTO requestDTO) {
        Product product = modelMapper.map(productService.getProductByName(requestDTO.getProductName()), Product.class);
        Warehouse warehouse = modelMapper.map(warehouseService.getWarehouseByName(requestDTO.getWarehouseName()), Warehouse.class);

        InventoryItem item = new InventoryItem();
        item.setProduct(product);
        item.setWarehouse(warehouse);
        item.setQuantity(requestDTO.getQuantity());
        item.setLastUpdated(LocalDateTime.now());

        return modelMapper.map(inventoryItemRepository.save(item), InventoryItemResponseDTO.class);
    }

    public InventoryItemResponseDTO getInventoryItemById(Long id) {
        return modelMapper.map(
                inventoryItemRepository.findById(id).orElseThrow(() -> new InventoryItemNotFound("Item not found")),
                InventoryItemResponseDTO.class
        );
    }

    public List<InventoryItemResponseDTO> getAllInventoryItems() {
        return inventoryItemRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, InventoryItemResponseDTO.class))
                .collect(Collectors.toList());
    }

    public InventoryItemResponseDTO updateInventoryItem(Long id, InventoryItemRequestDTO requestDTO) {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new InventoryItemNotFound("Item not found"));

        item.setQuantity(requestDTO.getQuantity());
        item.setLastUpdated(LocalDateTime.now());

        return modelMapper.map(inventoryItemRepository.save(item), InventoryItemResponseDTO.class);
    }

    public void deleteInventoryItem(Long id) {
        InventoryItem item = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new InventoryItemNotFound("Item not found"));
        inventoryItemRepository.delete(item);
    }
}