package com.allen.pojo.entity;

import lombok.Data;

@Data
public class CustomerEntity {
    private String id;
    private String name;
    private PersonEntity person;
}
