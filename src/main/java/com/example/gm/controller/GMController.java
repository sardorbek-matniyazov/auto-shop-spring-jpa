package com.example.gm.controller;

import com.example.gm.entity.GM;
import com.example.gm.request.GMRequest;
import com.example.gm.service.GMService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/gm")
@AllArgsConstructor
public class GMController {
    private final GMService service;

    @GetMapping(value = "/all")
    public List<GM> all(){
        return service.getAll();
    }

    @GetMapping(path = "{id}")
    public GM all(@PathVariable("id") Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public String add(@RequestBody GMRequest request){
        return service.add(request);
    }

    @PutMapping(path = "{id}")
    public String edit(@PathVariable("id") Long id, @RequestBody GMRequest request){
        return service.edit(id, request);
    }

    @DeleteMapping(path = "{id}")
    public String edit(@PathVariable("id") Long id){
        return service.delete(id);
    }
}
// 23:04 01.03.2022 )