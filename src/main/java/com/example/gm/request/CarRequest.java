package com.example.gm.request;

import lombok.Data;

@Data
public class CarRequest {
    private String model;
    private String price;
    private Long year;
    private Long autoShopId;
}