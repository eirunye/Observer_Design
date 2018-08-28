package com.define_observers.interfaces;

/**
 * Author YRG
 * Created by on 2018/8/22.
 * Describe 定义一个主题,就是一个被观察者
 */
public interface Subject {

    void registerObserver(Observer observe);
    void unRegisterObserver(Observer observe);
    void notifyObservers();

}
