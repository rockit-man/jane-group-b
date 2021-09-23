package com.example.helpingout.repositories;

import com.example.helpingout.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
}
