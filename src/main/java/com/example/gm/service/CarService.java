package com.example.gm.service;

import com.example.gm.entity.Car;
import com.example.gm.repository.CarRepository;
import com.example.gm.repository.ShopRepository;
import com.example.gm.request.CarRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {
    private final CarRepository repo;
    private final ShopRepository shopRepository;

    public List<Car> getAll() {
        return repo.findAll();
    }

    public Car get(Long id) {
        Optional<Car> byId = repo.findById(id);
        return byId.orElseGet(() -> Car.builder()
                .model("the car don't find")
                .build());
    }

    public String add(CarRequest request) {
        if (request.getAutoShopId() == null ||
                request.getModel() == null ||
                request.getPrice() == null ||
                request.getYear() == null)
            return "Oops, something went wrong";

        if (!shopRepository.existsById(request.getAutoShopId()))
            return "shop don't find";

        repo.save(
                Car.builder()
                        .year(request.getYear())
                        .price(request.getPrice())
                        .model(request.getModel())
                        .shop(shopRepository.getById(request.getAutoShopId()))
                        .build()
        );

        return "successfully added";
    }

    public String edit(Long id, CarRequest request) {
        if (request.getAutoShopId() == null ||
        request.getModel() == null ||
        request.getPrice() == null ||
        request.getYear() == null)
            return "Oops, something went wrong";

        if (!shopRepository.existsById(request.getAutoShopId()))
            return "shop don't find";
        if (!repo.existsById(id))
            return "car don't find";

        repo.save(
                Car.builder()
                        .id(id)
                        .year(request.getYear())
                        .price(request.getPrice())
                        .model(request.getModel())
                        .shop(shopRepository.getById(request.getAutoShopId()))
                        .build()
        );

        return "successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id))
            return "car don't find";

        repo.deleteById(id);

        return "successfully deleted";
    }

    public List<Car> getByShopId(Long id) {
        return repo.findAllByShopId(id);
    }
}