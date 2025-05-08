package com.erp.inventory.controller;

import com.erp.inventory.dto.UserCreateRequestDTO;
import com.erp.inventory.dto.UserResponseDTO;
import com.erp.inventory.service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @PostMapping("create-user")
    public  ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateRequestDTO request) {
        return ResponseEntity.ok(adminService.createUser(request));
    }

}

