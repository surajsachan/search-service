package com.example.search_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Product {

    private Integer id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
}







