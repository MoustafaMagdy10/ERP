package com.erp.inventory.controller;

import com.erp.inventory.dto.UserCreateRequestDTO;
import com.erp.inventory.dto.UserResponseDTO;
import com.erp.inventory.model.Role;
import com.erp.inventory.service.AdminService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @PostMapping("create-user")
    public  ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateRequestDTO request) {
        return ResponseEntity.ok(adminService.createUser(request));
    }

    @GetMapping("get-all-users")
    public  ResponseEntity<Iterable<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }
    @GetMapping("get-user-/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public  ResponseEntity<UserResponseDTO> getUserByName(@PathVariable @Valid String username) {
        return ResponseEntity.ok(adminService.getUserByName(username).get());
    }
    @DeleteMapping("delete-user-/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable @Valid @NotBlank String username) {
        adminService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody @Valid Role role) {
        Role createdRole = adminService.createRole(role);
        return ResponseEntity.ok(createdRole);

    }

    @GetMapping("get-all-roles")
    public  ResponseEntity<Iterable<Role>> getAllRoles() {
        return ResponseEntity.ok(adminService.getAllRoles());
    }


    @PutMapping("/roles/{roleName}")
    public ResponseEntity<Role> updateRole( @RequestBody @Valid Role role) {
        Role updatedRole = adminService.updateRole(role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("delete-role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void DeleteRole(@RequestBody @Valid Role role) {
        adminService.DeleteRole(role);
    }
}

