package com.erp.inventory.service;

import com.erp.inventory.dto.LoginResponseDTO;
import com.erp.inventory.dto.UserLoginDTO;
import com.erp.inventory.dto.UserResponseDTO;
import com.erp.inventory.model.Role;
import com.erp.inventory.model.User;
import com.erp.inventory.repository.RoleRepository;
import com.erp.inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.erp.inventory.dto.UserRegistrationDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);



    @Transactional
    public UserResponseDTO register(UserRegistrationDTO request) {
        System.out.println("Registering new user...");

        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Fetch roles from DB
        Set<Role> roles = request.getRoles() != null
                ? request.getRoles().stream()
                .map(roleName -> {
                    Role role = roleRepository.findByName(roleName)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                    System.out.println("Found role: " + role.getName());
                    return role;
                })
                .collect(Collectors.toSet())
                : new HashSet<>(); // Use HashSet instead of emptySet to avoid immutability issues

        // Debug log to verify roles set
        System.out.println("Roles to assign: " + roles.stream()
                .map(Role::getName).collect(Collectors.joining(", ")));

        // Build user with encoded password and assigned roles
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();

        // Save user
        System.out.println("Saving user...");
        userRepository.save(user);
        System.out.println("User saved successfully.");

        // Create response DTO
        List<String> roleNames = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return new UserResponseDTO(user.getUsername(), user.getEmail(), roleNames);
    }





    public LoginResponseDTO login(UserLoginDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Invalid credentials");
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user.getUsername());
        return new LoginResponseDTO(token);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public String getRole(String username){
        return userRepository.findByUsername(username).get().getRoles().iterator().next().getName();
    }
}


