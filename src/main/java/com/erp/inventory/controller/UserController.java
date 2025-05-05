package com.erp.inventory.controller;

import com.erp.inventory.dto.LoginResponseDTO;
import com.erp.inventory.dto.UserLoginDTO;
import com.erp.inventory.dto.UserRegistrationDTO;
import com.erp.inventory.dto.UserResponseDTO;
import com.erp.inventory.model.User;
import com.erp.inventory.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final  UserService userService;

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> test(@RequestBody @Valid UserRegistrationDTO request) {
        System.out.println("register");
        return ResponseEntity.ok(userService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UserLoginDTO request) {
        return ResponseEntity.ok(userService.login(request));
    }
    @GetMapping("/my-roles")
    public ResponseEntity<String> getMyRoles(@RequestBody String username) {
        return ResponseEntity.ok(userService.getRole(username));
    }

}
