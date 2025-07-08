package com.erp.inventory.service;

import com.erp.inventory.dto.UserCreateRequestDTO;
import com.erp.inventory.dto.UserResponseDTO;
import com.erp.inventory.exception.RoleNotFound;
import com.erp.inventory.exception.UserNotFoundException;
import com.erp.inventory.model.Product;
import com.erp.inventory.model.Role;
import com.erp.inventory.model.User;
import com.erp.inventory.repository.RoleRepository;
import com.erp.inventory.repository.UserRepository;
import com.erp.inventory.repository.ProductRepository;
import com.erp.inventory.utils.PasswordUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.relation.RoleNotFoundException;
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
    private final ProductRepository productRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final ModelMapper modelMapper;

    @Transactional
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

        return modelMapper.map(user,UserResponseDTO.class);
    }

    public List<UserResponseDTO> getAllUsers() {
      return  userRepository.findAll().stream().map(user->modelMapper.map(user,UserResponseDTO.class)).collect(Collectors.toList());
    }


    public Optional<UserResponseDTO> getUserByName(@PathVariable @NotBlank String username) {
        return userRepository.findByUsername(username)
                .map(user -> modelMapper.map(user, UserResponseDTO.class));
    }

    public void deleteUser(@PathVariable @NotBlank String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username + "does not exist in the database. Please try again with a valid username."));
        userRepository.delete(user);
    }
}
