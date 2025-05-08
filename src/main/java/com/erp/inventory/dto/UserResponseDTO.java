package com.erp.inventory.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDTO {
    private  String username;
    private  String email;
    private  List<String> roles;



}