package com.example.gm.repository;

import com.example.gm.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    boolean existsByFirstnameAndLastname(String firstname, String lastname);
    Director getByFirstnameAndLastname(String firstname, String lastname);
}
