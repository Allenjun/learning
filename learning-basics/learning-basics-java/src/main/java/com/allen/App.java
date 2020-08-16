package com.allen;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    
    public static void main(String[] args) {
        Map<Object, Object> map1 = new HashMap<>();
        map1.put(1, 2);
        
        Map<Object, Object> map2 = new HashMap<>(1);
        map2.put(1, 2);
        
        int a = 0;
        int b = 0;
        if (a == 0) {
            System.out.println("1");
        } else if (b == 0) {
            System.out.println("2");
        }
    }
}
