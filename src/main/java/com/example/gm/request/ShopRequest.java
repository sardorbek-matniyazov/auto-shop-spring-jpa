package com.example.gm.request;

import com.example.gm.entity.Address;
import lombok.Data;

@Data
public class ShopRequest {
    private String title;
    private Address address;
    private Long gmId;
}
