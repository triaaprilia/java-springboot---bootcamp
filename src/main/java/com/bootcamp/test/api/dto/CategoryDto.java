package com.bootcamp.test.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class CategoryDto {
    @Data
    public static class Save{
        @NotEmpty(message = "String tidak boleh kosong")
        @NotNull(message = "Tidak boleh kosong")
        private String name;
    }
}
