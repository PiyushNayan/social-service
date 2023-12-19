package com.example.product_service.entity;

import lombok.Data;

import java.util.ArrayList;


@Data
public class Merchant {
    private String merchantId;
    private String merchantName;
    private ArrayList<String> productId= new ArrayList<String>();
    private int sellingCount;
    private String merchantEmail;
    private int rating;
    private int stocks;
    private int price;
}
