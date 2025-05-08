package com.erp.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
@Data
public class UserCreateRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String email;

    private List<String> roles;
}
