package com.user.userservice.service;


import com.user.userservice.model.ERole;
import com.user.userservice.model.Role;
import com.user.userservice.model.User;
import com.user.userservice.repository.RoleRepository;
import com.user.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Boolean createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            return false;

        Set<Role> roles = new HashSet<>();

        roles.add(roleRepository.findByName(ERole.ROLE_GENERAL).orElseThrow(() -> new RuntimeException("Role is not Found")));

        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }

    public Boolean loginUser(String email, String password) {
        if (userRepository.existsByEmail(email))
            return userRepository.findByEmail(email).getPassword().equals(password);
        return false;
    }
}