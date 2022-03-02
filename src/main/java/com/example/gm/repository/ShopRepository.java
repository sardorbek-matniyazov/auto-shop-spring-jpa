package com.example.gm.repository;

import com.example.gm.entity.AutoShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<AutoShop, Long> {
}
