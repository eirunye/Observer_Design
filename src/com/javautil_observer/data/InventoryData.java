package com.javautil_observer.data;

import java.util.Observable;

/**
 * Author Eirunye
 * Created by on 2018/8/24.
 * Describe 库存数据信息
 */
public class InventoryData extends Observable {

    private int valuableProductNum; //库存有贵重产品
    private int normalProductNum;   //普通产品

    public void setCurrentData(int valuableProductNum, int normalProductNum) {
        this.valuableProductNum = valuableProductNum;
        this.normalProductNum = normalProductNum;
        statusChange();
    }

    private void statusChange() {
        setChanged();
        notifyObservers();
    }

    public int getValuableProductNum() {
        return valuableProductNum;
    }

    public void setValuableProductNum(int valuableProductNum) {
        this.valuableProductNum = valuableProductNum;
    }

    public int getNormalProductNum() {
        return normalProductNum;
    }

    public void setNormalProductNum(int normalProductNum) {
        this.normalProductNum = normalProductNum;
    }
}
