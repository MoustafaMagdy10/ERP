package com.erp.inventory.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private final String username;
    private final String email;
    private final List<String> roles;
}