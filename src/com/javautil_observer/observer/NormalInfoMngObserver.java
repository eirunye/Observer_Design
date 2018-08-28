package com.javautil_observer.observer;

import com.javautil_observer.data.InventoryData;
import com.javautil_observer.interfaces.DisplayIllustrate;

import java.util.Observable;
import java.util.Observer;

/**
 * Author Eirunye
 * Created by on 2018/8/28.
 * Describe 普通信息管理观察者
 */
public class NormalInfoMngObserver implements Observer, DisplayIllustrate {

    private Observable observable;

    private int valuableProductNum; //库存有贵重产品
    private int normalProductNum;   //普通产品

    public NormalInfoMngObserver(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void deleteObserver(){
        observable.deleteObserver(this);
    }

    @Override
    public void disPlay() {
        System.out.println("普通产品发生改变了:" + normalProductNum);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof InventoryData) {
            InventoryData inventoryData = (InventoryData) o;
            this.normalProductNum = inventoryData.getNormalProductNum();
            disPlay();
        }

    }
}
