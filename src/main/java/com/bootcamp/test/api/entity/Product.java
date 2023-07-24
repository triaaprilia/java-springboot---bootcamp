package com.bootcamp.test.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Integer id;
    private String name;
    private Integer category_id;
    private Integer stock;
}
