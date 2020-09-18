package com.allen.objectCopy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JUN @Description TODO
 * @createTime 15:22
 */
@Data
public class Address implements Cloneable, Serializable {

    private String addr;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
