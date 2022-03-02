package com.example.gm.repository;

import com.example.gm.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsByTitleAndCityAndNumber(String title, String city, Long number);
    Address getByTitleAndCityAndNumber(String title, String city, Long number);
}
