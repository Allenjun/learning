package com.allen.observer;

/**
 * @author JUN
 * @Description TODO
 * @createTime 13:09
 */
public class EatRice extends AbstractSubject {
    
    public void finsh() {
        System.out.println("吃完啦");
        this.notifyObservers();
    }
}
