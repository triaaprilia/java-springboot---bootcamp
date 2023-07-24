package com.bootcamp.test.api.dto;

import lombok.Data;

public class ProductDto {

    @Data
    public static class Save{
        private Integer id;
        private String name;
        private Integer category_id;
        private Integer stock;
    }
}
