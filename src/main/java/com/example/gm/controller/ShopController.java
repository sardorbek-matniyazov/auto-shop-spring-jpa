package com.example.gm.controller;

import com.example.gm.entity.AutoShop;
import com.example.gm.request.ShopRequest;
import com.example.gm.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shop")
@AllArgsConstructor
public class ShopController {
    private final ShopService service;
    @GetMapping(value = "/all")
    public List<AutoShop> all(){
        return service.getAll();
    }

    @GetMapping(path = "{id}")
    public AutoShop all(@PathVariable("id") Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public String add(@RequestBody ShopRequest request){
        return service.add(request);
    }

    @PutMapping(path = "{id}")
    public String edit(@PathVariable("id") Long id, @RequestBody ShopRequest request){
        return service.edit(id, request);
    }

    @DeleteMapping(path = "{id}")
    public String edit(@PathVariable("id") Long id){
        return service.delete(id);
    }
}
