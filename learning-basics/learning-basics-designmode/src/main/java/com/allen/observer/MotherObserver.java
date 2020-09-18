package com.allen.observer;

/**
 * @author JUN @Description TODO
 * @createTime 13:11
 */
public class MotherObserver implements Observer {

    @Override
    public void update(Object args) {
        System.out.println("妈：“再吃一碗”");
    }
}
