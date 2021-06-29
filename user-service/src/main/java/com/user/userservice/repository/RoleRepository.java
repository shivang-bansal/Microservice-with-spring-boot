package com.user.userservice.repository;

import com.user.userservice.model.ERole;
import com.user.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(ERole name);
    Optional<Role> findById(Integer id);
}
