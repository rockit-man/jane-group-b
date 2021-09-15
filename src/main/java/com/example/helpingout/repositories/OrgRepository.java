package com.example.helpingout.repositories;

import java.util.Optional;
import com.example.helpingout.models.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository <Org, String> {

}

