package com.bootcamp.test.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private Integer id;
    private String name;
    private String email;
    private String phone;

}
