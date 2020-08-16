package com.allen.clone;

import com.allen.clone.pojo.Person;

/**
 * @author JUN
 * @Description TODO
 * @createTime 15:21
 */
public class ReflectCloneTests {
    
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.allen.clone.pojo.Person");
        Person person = (Person) aClass.newInstance();
        System.out.println(person);
    }
    
}
