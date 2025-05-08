package com.erp.inventory.service;

import com.erp.inventory.dto.UserCreateRequestDTO;
import com.erp.inventory.dto.UserRegistrationDTO;
import com.erp.inventory.dto.UserResponseDTO;
import com.erp.inventory.model.Role;
import com.erp.inventory.model.User;
import com.erp.inventory.repository.RoleRepository;
import com.erp.inventory.repository.UserRepository;
import com.erp.inventory.utils.PasswordUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);


    public UserResponseDTO createUser(UserCreateRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        Set<Role> roles = request.getRoles() != null ? request.getRoles().stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet()) : Collections.emptySet();

        String tempPassword = PasswordUtils.generateTempPassword();

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(PasswordUtils.encodeTempPassword(tempPassword))
                .roles(roles)
                .forcePasswordChange(true)
                .active(true)
                .build();


        System.out.println("saving....");
        System.out.println(user.getUsername() + " " + user.getPassword() + " " + user.getEmail() + " " + user.getRoles() + " " + user.isForcePasswordChange() + " " + user.isActive() );
        userRepository.save(user);
        System.out.println("--saved--");

        // null-safe role list mapping
        List<String> roleNames = Optional.ofNullable(user.getRoles())
                .orElse(Collections.emptySet())
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        return new UserResponseDTO(user.getUsername(), user.getEmail(), roleNames);
    }


}
