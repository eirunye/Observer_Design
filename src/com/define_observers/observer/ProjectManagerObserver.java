package com.define_observers.observer;

import com.define_observers.interfaces.DisplaySchedule;
import com.define_observers.interfaces.Observer;
import com.define_observers.interfaces.Subject;

/**
 * Author YRG
 * Created by on 2018/8/22.
 * Describe
 */
public class ProjectManagerObserver implements Observer, DisplaySchedule {

    private Subject developmentProgressSubject;

    private int completeProgress;//完成进度
    private int updateProgress;//更新进度

    public ProjectManagerObserver(Subject developmentProgressSubject) {
        this.developmentProgressSubject = developmentProgressSubject;
        developmentProgressSubject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("项目管理真显示当前数据完成进度为: " + completeProgress + "更新修改进度为:" + updateProgress);
    }


    @Override
    public void update(int completeProgress, int updateProgress) {
        this.completeProgress = completeProgress;
        this.updateProgress = updateProgress;
        display();
    }
}
