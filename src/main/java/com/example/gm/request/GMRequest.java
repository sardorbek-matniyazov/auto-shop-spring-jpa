package com.example.gm.request;

import com.example.gm.entity.Address;
import com.example.gm.entity.Director;
import lombok.Data;

@Data
public class GMRequest {
    private String compName;
    private Address address;
    private Director director;
}
