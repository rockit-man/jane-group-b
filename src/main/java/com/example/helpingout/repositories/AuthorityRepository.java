package com.example.helpingout.repositories;

import com.example.helpingout.models.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

}
