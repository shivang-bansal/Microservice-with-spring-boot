package com.user.userservice.service;


import com.user.userservice.model.ERole;
import com.user.userservice.model.Role;
import com.user.userservice.model.User;
import com.user.userservice.repository.RoleRepository;
import com.user.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public Boolean createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        if(user.getRoles().size()==0)
        {
            roles.add(roleRepository.findByName(ERole.ROLE_GENERAL).orElseThrow(() -> new RuntimeException("Role is not Found")));
            user.setRoles(roles);
        }
        userRepository.save(user);
        return true;
    }

    public Boolean updateRole(Integer id){

        if (!userRepository.existsById(id))
            return false;

        Set<Role> roles = new HashSet<>();
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            User user=optionalUser.get();
            roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role is not Found")));
            user.setRoles(roles);
            userRepository.save(user);
        }catch (Exception e){
            throw new UsernameNotFoundException("Invalid id");
        }
        return true;
    }
}