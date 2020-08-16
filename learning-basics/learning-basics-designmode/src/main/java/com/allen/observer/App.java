package com.allen.observer;

/**
 * @author JUN
 * @Description TODO
 * @createTime 13:11
 */
public class App {
    
    public static void main(String[] args) {
        EatRice eatRice = new EatRice();
        eatRice.addObserver(new MotherObserver());
        eatRice.finsh();
    }
    
}
