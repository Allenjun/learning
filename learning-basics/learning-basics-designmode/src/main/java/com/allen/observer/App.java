package com.allen.observer;

import org.junit.Test;

/**
 * @author JUN @Description TODO
 * @createTime 13:11
 */
public class App {

    @Test
    public void test1() {
        EatRice eatRice = new EatRice();
        eatRice.addObserver(new MotherObserver());
        eatRice.finsh();
    }
}
