package com.bootcamp.test.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class ProductDto {

    @Data
    public static class Save{
        private Integer id;

        @NotEmpty(message = "String tidak boleh kosong")
        @NotNull(message = "Tidak boleh null")
        private String name;

        @NotNull(message = "Tidak boleh null")
        private Integer category_id;

        @Min(0)
        private Integer stock;
    }
}
