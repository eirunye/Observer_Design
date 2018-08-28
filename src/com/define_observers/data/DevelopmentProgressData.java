package com.define_observers.data;

import com.define_observers.interfaces.Observer;
import com.define_observers.interfaces.Subject;

import java.util.ArrayList;

/**
 * Author YRG
 * Created by on 2018/8/22.
 * Describe 完成进展情况
 */
public class DevelopmentProgressData implements Subject {

    private ArrayList arrayObserve;

    private int completeProgress;//完成进度
    private int updateProgress;//更新进度

    public DevelopmentProgressData() {
        arrayObserve = new ArrayList();
    }

    @Override
    public void registerObserver(Observer observer) {
        arrayObserve.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer observer) {
        int i = arrayObserve.indexOf(observer);
        if (i >= 0) {
            arrayObserve.remove(i);
        }

    }

    @Override
    public void notifyObservers() {

        for (int i = 0; i < arrayObserve.size(); i++) {
            Observer o = (Observer) arrayObserve.get(i);
            o.update(completeProgress, updateProgress);
        }
    }

    public void setCurrentData(int completeProgress, int updateProgress) {
        this.completeProgress = completeProgress;
        this.updateProgress = updateProgress;
        notifyObservers();
    }


}
