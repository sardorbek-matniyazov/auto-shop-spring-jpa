package com.example.gm.repository;

import com.example.gm.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "SELECT * FROM car c JOIN auto_shop s ON s.id=c.shop_id " +
            "WHERE c.shop_id=:id"
            ,nativeQuery = true)
    List<Car> findAllByShopId(Long id);
}
