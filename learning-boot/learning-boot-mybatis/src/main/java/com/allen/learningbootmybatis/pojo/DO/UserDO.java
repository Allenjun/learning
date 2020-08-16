package com.allen.learningbootmybatis.pojo.DO;

import lombok.Data;

/**
 * @author JUN
 * @Description TODO
 * @createTime 13:51
 */
@Data
public class UserDO {

    private Integer id;
    private String name;
    private String password;
    private Sex sex;
}
