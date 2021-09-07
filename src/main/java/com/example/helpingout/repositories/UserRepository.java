package com.example.helpingout.repositories;

import java.util.Optional;

import com.example.helpingout.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findById(Integer userId);
}