package com.bootcamp.test.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class UserProductDto {
    @Data
    public static class Save{
        private Integer id;

        @NotNull(message = "Tidak boleh null")
        private Integer user_id;

        @NotNull(message = "Tidak boleh null")
        private Integer product_id;

        @Min(0)
        private Integer quantity;
    }
}
