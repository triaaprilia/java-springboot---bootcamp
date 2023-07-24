package com.bootcamp.test.api.dto;

import lombok.Data;

public class UserProductDto {
    @Data
    public static class Save{
        private Integer id;
        private Integer user_id;
        private Integer product_id;
        private Integer quantity;
    }
}
