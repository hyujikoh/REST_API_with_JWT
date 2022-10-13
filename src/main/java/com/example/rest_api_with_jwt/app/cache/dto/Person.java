package com.example.rest_api_with_jwt.app.cache.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Person {
    private long id;
    private String name;
}