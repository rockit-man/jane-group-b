package com.example.helpingout.auth.repository;

import com.example.helpingout.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
