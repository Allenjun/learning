package com.allen.learningbootsecuritysimple.entity.DO;

import lombok.Data;

import javax.persistence.*;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/06/27 11:41:00
 */
@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
}
