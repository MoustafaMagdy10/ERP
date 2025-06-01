package com.erp.inventory.controller;

import com.erp.inventory.model.Role;
import com.erp.inventory.service.RoleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private RoleService roleService;
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody @Valid Role role) {
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.ok(createdRole);

    }

    @GetMapping
    public  ResponseEntity<Iterable<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }


    @PutMapping
    public ResponseEntity<Role> updateRole( @RequestBody @Valid Role role) {
        Role updatedRole = roleService.updateRole(role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void DeleteRole(@PathVariable @NotBlank String name ) {
        roleService.DeleteRole(name);
    }

}
