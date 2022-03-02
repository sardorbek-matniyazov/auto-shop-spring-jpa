package com.example.gm.controller;

import com.example.gm.entity.Car;
import com.example.gm.request.CarRequest;
import com.example.gm.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/car")
public class CarController {
    private final CarService service;

    @GetMapping("/all")
    public List<Car> getAll(){
        return service.getAll();
    }

    @GetMapping(path = "{id}")
    public Car get(@PathVariable(value = "id") Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public String add(@RequestBody CarRequest request){
        return service.add(request);
    }

    @PutMapping(path = "{id}")
    public String edit(@PathVariable("id") Long id, @RequestBody CarRequest request){
        return service.edit(id, request);
    }

    @DeleteMapping(path = "{id}")
    public String delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

    @GetMapping(value = "/shopid/{id}")
    public List<Car> getByShopId(@PathVariable Long id){
        return service.getByShopId(id);
    }
}
