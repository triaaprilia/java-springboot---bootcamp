package com.bootcamp.test.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProduct {

    private Integer id;
    private Integer user_id;
    private Integer product_id;
    private Integer quantity;
}
