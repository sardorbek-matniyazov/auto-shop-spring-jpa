package com.example.gm.repository;

import com.example.gm.entity.GM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GMRepository extends JpaRepository<GM, Long> {
    @Override
    GM getById(Long aLong);
}
