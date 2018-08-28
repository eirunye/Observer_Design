package com.javautil_observer.observer;

import com.javautil_observer.data.InventoryData;
import com.javautil_observer.interfaces.DisplayIllustrate;

import java.util.Observable;
import java.util.Observer;

/**
 * Author Eirunye
 * Created by on 2018/8/28.
 * Describe
 */
public class BossMngObserver implements Observer, DisplayIllustrate {

    private Observable observable;
    private int valuableProductNum; //库存有贵重产品
    private int normalProductNum;   //普通产品

    public BossMngObserver(Observable observable) {
        this.observable = observable;
        //添加绑定当前的观察者
        observable.addObserver(this);
    }

    /**
     * 删除当前的观察者
     */
    public void deleteObserver(){
        observable.deleteObserver(this);
    }

    @Override
    public void disPlay() {

        System.out.println("总经理观察数据改变:贵重产品数量: " + valuableProductNum + "普通产品数量： " + normalProductNum);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof InventoryData) {
            InventoryData inventoryData = (InventoryData) o;
            this.valuableProductNum = inventoryData.getValuableProductNum();
            this.normalProductNum = inventoryData.getNormalProductNum();
            disPlay();
        }
    }
}
