package com.erp.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WarehouseRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String location;
}
