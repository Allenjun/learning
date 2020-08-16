package com.allen.clone;

import com.allen.clone.pojo.Address;
import com.allen.clone.pojo.Person;

/**
 * @author JUN
 * @Description TODO
 * @createTime 15:19
 */
public class MethodCloneTests {
    
    public static void main(String[] args) {
        Address address = new Address();
        address.setAddr("123是");
        Person person = new Person();
        person.setAddress(address);
        person.setName("allen");
        
        try {
            Person clone = (Person) person.clone();
            System.out.println(clone == person);
            System.out.println(clone.getAddress() == person.getAddress());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
    }
    
}
