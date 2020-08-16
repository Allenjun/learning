package com.allen.clone;

import com.allen.clone.pojo.Address;
import com.allen.clone.pojo.Person;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author JUN
 * @Description TODO
 * @createTime 15:20
 */
public class SerializableCloneTests {
    
    public static void main(String[] args) throws Exception {
        Address address = new Address();
        address.setAddr("123是");
        Person person = new Person();
        person.setAddress(address);
        person.setName("allen");
        
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:/1.ser"));
        objectOutputStream.writeObject(person);
        
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:/1.ser"));
        Person readObject = (Person) objectInputStream.readObject();
        System.out.println(readObject == person);
        System.out.println(readObject.getName());
    }
    
}
