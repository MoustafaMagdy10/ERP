package com.erp.inventory.service;

import com.erp.inventory.exception.RoleNotFound;
import com.erp.inventory.model.Role;
import com.erp.inventory.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    public Role createRole(@RequestBody Role role){
        return roleRepository.save(role);
    }

    public void DeleteRole(@PathVariable String name){
        roleRepository.delete(roleRepository.findByName(name).orElseThrow(()->new RoleNotFound("Role Not Found")));
    }

    public Role updateRole(@RequestBody Role role){
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }


}
