package com.allen.objectCopy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JUN @Description TODO
 * @createTime 15:21
 */
@Data
public class Person implements Cloneable, Serializable {

    private String name;
    private Address address;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        person.setAddress((Address) address.clone());
        return person;
    }
}
