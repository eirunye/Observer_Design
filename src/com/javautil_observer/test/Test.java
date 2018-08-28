package com.javautil_observer.test;

import com.javautil_observer.data.InventoryData;
import com.javautil_observer.observer.BossMngObserver;
import com.javautil_observer.observer.NormalInfoMngObserver;
import com.javautil_observer.observer.ValuableInfoMngObserver;

/**
 * Author Eirunye
 * Created by on 2018/8/28.
 * Describe 这是运用java包里面的观察者模式
 *
 * 实现步骤：
 * 1.首先观察者需要实现java.util.Observer,然后将其被观察者=>java.util.Observaerable作为其观察者的构造函数
 *   <1>、通过observeable.addObserver(this)添加观察者
 *   <2>、实现 Observer更新数据方法
 *   看本例的包下的observe的三个类
 *
 * 2.被观察者需要继承java.util.Observerable,
 *
 *  <1>、然后先调用setChanged()方法，
 *  <2>、在进行调用notifyObserves()更新数据
 *
 * 3.测试 Test.class
 *
 */
public class Test {
    public static void main(String[] args) {

        InventoryData inventoryData = new InventoryData();

        ValuableInfoMngObserver io = new ValuableInfoMngObserver(inventoryData);

//        io.deleteObserve();
        inventoryData.setCurrentData(20, 30);

        NormalInfoMngObserver no = new NormalInfoMngObserver(inventoryData);

//        no.deleteObserver();
        inventoryData.setCurrentData(15, 27);

        BossMngObserver bossMngObserver = new BossMngObserver(inventoryData);

        inventoryData.setCurrentData(10, 50);



    }
}
