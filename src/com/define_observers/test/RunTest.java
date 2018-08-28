package com.define_observers.test;

import com.define_observers.data.DevelopmentProgressData;
import com.define_observers.observer.ProductManagerObserver;
import com.define_observers.observer.ProjectManagerObserver;

/**
 * Author YRG
 * Created by on 2018/8/22.
 * Describe 测试类
 *
 *
 *   自定义一个观察者模式
 *
 *   1.构造一个主题Subject或者是一个被观察者Observeable，这是一个接口
 *   接口方法有:
 *   注册观察者
 *   registerObserver(Observer o)
 *   解注册观察者
 *   unRegisterObserve(Observer o)
 *   更新数据
 *   notifyObservers();
 *
 *   2.构建一个被观察者实现改主题接口如本例的 DevelopmentProgressData.class
 *   在registerObserver(Observer o)方法中将观察者添加到注册列表中
 *   在 unRegisterObserve(Observer o)删除观察者
 *
 *
 *   3.构建一个观察者接口Observer
 *   方法：
 *
 *   update(); //更新数据
 *
 *   4.可构建一个展示数据的接口（可忽略）
 *   有展示数据的方法，观察者要实现这个方法 查看本例的 DisplaySchedule
 *
 *   4.定义观察者，需实现Observes，DisplaySchedule(可忽略)接口，
 *   <1>、将主题Subject设置为观察者的属性，并将其作为观察者的构造函数如 ProductManagerObserver.class
 *   调用  developmentProgressSubject.registerObserver(this);将观察者注册到观察列表中
 *
 *   5.测试 RunTest.class
 */
public class RunTest {

    public static void main(String[] args) {

        DevelopmentProgressData developmentProgressData = new DevelopmentProgressData();
        ProductManagerObserver productManagerObserver = new ProductManagerObserver(developmentProgressData);
        ProjectManagerObserver projectManagerObserver = new ProjectManagerObserver(developmentProgressData);
        developmentProgressData.setCurrentData(34, 45);
        //当项目经理出差了，不观察项目进度了就取消订阅了
        developmentProgressData.unRegisterObserver(projectManagerObserver);
        developmentProgressData.setCurrentData(46, 90);
    }


}
