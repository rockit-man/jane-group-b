package com.example.helpingout.repositories;

import com.example.helpingout.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
