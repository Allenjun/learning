package com.allen.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersonEntity {
    private String id;
    private SexType sexType;
    private Integer age;
    private Date born;
}
