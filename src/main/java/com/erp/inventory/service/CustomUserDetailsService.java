package com.erp.inventory.service;

import com.erp.inventory.exception.ForcePasswordChangeException;
import com.erp.inventory.model.User;
import com.erp.inventory.model.UserPrincipal;
import com.erp.inventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }

//        if(user.get().isForcePasswordChange()) {
//            throw new ForcePasswordChangeException("Password change required");
//        }
        return new UserPrincipal(user.get());
    }


}
