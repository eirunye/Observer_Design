package com.define_observers.observer;

import com.define_observers.interfaces.DisplaySchedule;
import com.define_observers.interfaces.Observer;
import com.define_observers.interfaces.Subject;

/**
 * Author YRG
 * Created by on 2018/8/22.
 * Describe
 */
public class ProductManagerObserver implements Observer, DisplaySchedule {

    private int completeProgress;//完成进度
    private int updateProgress;//更新进度

    private Subject developmentProgressSubject;

    public ProductManagerObserver(Subject developmentProgressSubject) {
        this.developmentProgressSubject = developmentProgressSubject;
        developmentProgressSubject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("产品经理管理者显示当前数据 完成进度为: " + completeProgress + "更新修改进度为:" + updateProgress);
    }


    @Override
    public void update(int completeProgress, int updateProgress) {
        this.completeProgress = completeProgress;
        this.updateProgress = updateProgress;
        display();
    }
}
