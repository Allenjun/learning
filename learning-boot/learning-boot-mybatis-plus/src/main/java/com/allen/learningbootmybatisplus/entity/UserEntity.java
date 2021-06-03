package com.allen.learningbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@TableName("user")
public class UserEntity extends BaseEntity{
    private String name;
    private Integer age;
    private String email;
}
