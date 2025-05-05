package com.erp.inventory.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

@lombok.Data
public class UserRegistrationDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private List<String> roles;

}
