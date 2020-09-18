package com.allen.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JUN @Description TODO
 * @createTime 13:00
 */
public abstract class AbstractSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(x -> x.update("update"));
    }
}
