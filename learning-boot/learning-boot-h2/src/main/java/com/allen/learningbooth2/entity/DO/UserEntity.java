package com.allen.learningbooth2.entity.DO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/06/27 11:41:00
 */
@Entity
@Table(name = "ali_user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

}
