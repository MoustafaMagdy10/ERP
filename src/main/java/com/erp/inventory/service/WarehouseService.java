package com.erp.inventory.service;

import com.erp.inventory.dto.WarehouseRequestDTO;
import com.erp.inventory.dto.WarehouseResponseDTO;
import com.erp.inventory.exception.WarehouseNotFound;
import com.erp.inventory.model.Warehouse;
import com.erp.inventory.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ModelMapper modelMapper;

    public WarehouseResponseDTO createWarehouse(WarehouseRequestDTO warehouseDTO) {
        Warehouse warehouse = modelMapper.map(warehouseDTO, Warehouse.class);
        return modelMapper.map(warehouseRepository.save(warehouse), WarehouseResponseDTO.class);
    }

    public WarehouseResponseDTO getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFound("Warehouse not found"));
        return modelMapper.map(warehouse, WarehouseResponseDTO.class);
    }

    public List<WarehouseResponseDTO> getAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(warehouse -> modelMapper.map(warehouse, WarehouseResponseDTO.class))
                .collect(Collectors.toList());
    }

    public WarehouseResponseDTO getWarehouseByName(String name) {
        Warehouse warehouse = warehouseRepository.findByName(name)
                .orElseThrow(() -> new WarehouseNotFound("Warehouse not found"));
        return modelMapper.map(warehouse, WarehouseResponseDTO.class);
    }

    public WarehouseResponseDTO updateWarehouse(WarehouseRequestDTO warehouseDTO) {
        Warehouse warehouse = warehouseRepository.findByName(warehouseDTO.getName())
                .orElseThrow(() -> new WarehouseNotFound("Warehouse not found"));

        warehouse.setLocation(warehouseDTO.getLocation());
        return modelMapper.map(warehouseRepository.save(warehouse), WarehouseResponseDTO.class);
    }

    public void deleteWarehouse(String name) {
        Warehouse warehouse = warehouseRepository.findByName(name)
                .orElseThrow(() -> new WarehouseNotFound("Warehouse not found"));
        warehouseRepository.delete(warehouse);
    }
}
