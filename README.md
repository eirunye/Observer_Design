# 观察者模式

# 简介

1.学习本篇博文，我们知道在什么场景下使用观察者模式。
2.观察者模式的优缺点。
3.观察者模式给我们在今后开发中什么思想。

# 场景

某家科技公司目前在开发一个项目，设计小组需要上报项目的进度给部门经理，主要有两个模块更新原有的业务模块进度和添加新业务的模块完成进度。
那么部门经理需要什么途径能最快的得到进度的信息呢？或者可能是项目总监也想参与，去获取到最新的进展情况呢？我们改如何去考虑这个业务呢？

很多时候我们就会想到继承去解决这一情况，毕竟OO编写思想时刻影响这一我们，但是如果某天项目经理出差了，他不想看项目进度了，那我们又改怎么办呢？

我们能不能就是设计一个业务，让能想知道该进度的人，不管什么时候，只要有开发者上报进度的时候就一下他就得到进度的信息，而他无需去关系这个过程。

你是否能想到合适的解决方案呢？

答案就在下文中，你准备好了吗？


# 问题

## 什么是观察者模式？

观察者模式定义一系列对象之间的一对多关系，当一个对象改变、更新状态时，依赖它的都会收到通知改变或者更新。

## 为什么需要观察者模式?

从定义中我们可以知道观察者模式当对象改变时，其他依赖的对象都会收到改变信息的状态。

从本例分析项目经理想知道进度情况，他只需要绑定进度，他就可以知道进度信息了，而无需关心如何操作，如果再增加一个想知道进度信息老板呢？也很容易，也让老板绑定进度信息数据就好了，不想知道的时候就解除绑定，就不在获取进度信息了。

所以在本案例场景中，观察者是我们这个场景非常合适的设计。

# 如何实现观察者模式？

## 自定义观察者模式

### 实现如下

> 我们先来看一下这个UML类图进行分析

![observer.png](https://upload-images.jianshu.io/upload_images/3012005-ee86c1da81dd578e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/600)

> 具体实现步骤

1.构造一个主题Subject或者是一个被观察者Observeable，这是一个接口或者是抽象类

 ```
   public interface Subject {
    //注册观察者
    void registerObserver(Observer observe);
    //解除绑定观察者
    void unRegisterObserver(Observer observe);
    //更新数据
    void notifyObservers();
  }
 ```

 2.构建一个被观察者实现该主题接口如本例的 DevelopmentProgressData.class，这里是进度信息数据
  在registerObserver(Observer o);//方法中将观察者添加到注册列表中
  在unRegisterObserve(Observer o);//删除观察者
 ```
 public class DevelopmentProgressData implements Subject {
  @Override
    public void registerObserver(Observer observer) {
        //将观察者添加到列表中
        arrayObserve.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer observer) {
        int i = arrayObserve.indexOf(observer);
        if (i >= 0) {
            //将观察者从列表中解除
            arrayObserve.remove(i);
        }

    }
    //通知所以观察者数据更新了
    @Override
    public void notifyObservers() {

        for (int i = 0; i < arrayObserve.size(); i++) {
            Observer o = (Observer) arrayObserve.get(i);
            o.update(completeProgress, updateProgress);
        }
    }
 }
 ```

3.构建一个观察者接口Observer

```
 public interface Observer {
    //更新数据
    void update(int completeProgress, int updateProgress);
 }
```

4.可构建一个展示数据的接口（可忽略）
 有展示数据的方法，观察者要实现这个方法 查看本例的 DisplaySchedule
 ```
public interface DisplaySchedule {
    void display();
}
 ```

5.定义观察者(模拟该类就是产品经理观察者)，需实现接口Observes、DisplaySchedule(可忽略)，
<1>、将主题Subject设置为观察者的属性，并将其作为观察者的构造函数如 ProductManagerObserver.class
调用  developmentProgressSubject.registerObserver(this);将观察者注册到观察列表中
```
public class ProductManagerObserver implements Observer, DisplaySchedule {

    private int completeProgress;//完成进度
    private int updateProgress;//更新进度
    //将主题当成观察者的属性
    private Subject developmentProgressSubject;

    public ProductManagerObserver(Subject developmentProgressSubject) {
        this.developmentProgressSubject = developmentProgressSubject;
        //注册该观察者
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

```
> 测试

RunTest.class
```
public class RunTest {

    public static void main(String[] args) {

        DevelopmentProgressData developmentProgressData = new DevelopmentProgressData();
        ProductManagerObserver productManagerObserver = new ProductManagerObserver(developmentProgressData);
        ProjectManagerObserver projectManagerObserver = new ProjectManagerObserver(developmentProgressData);
        developmentProgressData.setCurrentData(34, 45);
        //当项目经理出差了，不观察项目进度了就取消订阅了
        developmentProgressData.unRegisterObserver(projectManagerObserver);
        //当前只有产品经理获取到数据
        developmentProgressData.setCurrentData(46, 90);
    }
}
```
> 输出结果

```
C:\Java\jdk1.8.0_161\bin\...
产品经理管理者显示当前数据 完成进度为: 34更新修改进度为:45
项目管理真显示当前数据完成进度为: 34更新修改进度为:45
产品经理管理者显示当前数据 完成进度为: 46更新修改进度为:90

Process finished with exit code 0
```


## 根据java.util.observerable包下的Observerable.class实现观察者模式功能

### 实现如下

> 具体实现步骤

 1.首先观察者需要实现java.util.Observer,然后将其被观察者=>java.util.Observaerable作为其观察者的构造函数

   <1>、通过observeable.addObserver(this)添加观察者
   ```
   public class BossMngObserver implements Observer, DisplayIllustrate {

    private Observable observable;
    private int valuableProductNum; //库存有贵重产品
    private int normalProductNum;   //普通产品

    public BossMngObserver(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void disPlay() {
        System.out.println("总经理观察数据改变:贵重产品数量: " + valuableProductNum + "普通产品数量： " + normalProductNum);
    }
   //<2>、实现 Observer更新数据方法
   //看本例的包下的observe的三个类
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
   ```

 2.被观察者需要继承java.util.Observerable,

   <1>、然后先调用setChanged()方法

   <2>、在进行调用notifyObserves()更新数据

```
  public class InventoryData extends Observable {

    private int valuableProductNum; //库存有贵重产品
    private int normalProductNum;   //普通产品

    public void setCurrentData(int valuableProductNum, int normalProductNum) {
        this.valuableProductNum = valuableProductNum;
        this.normalProductNum = normalProductNum;
        statusChange();
    }

    private void statusChange() {
    //先调用 setChanged();
        setChanged();
        notifyObservers();
    }
 }
 ```

> 测试
3.Test.class
```
public class Test {
    public static void main(String[] args) {

        InventoryData inventoryData = new InventoryData();

        ValuableInfoMngObserver io = new ValuableInfoMngObserver(inventoryData);

        //io.deleteObserve();
        inventoryData.setCurrentData(20, 30);

        NormalInfoMngObserver no = new NormalInfoMngObserver(inventoryData);

        //no.deleteObserver();
        inventoryData.setCurrentData(15, 27);

        BossMngObserver bossMngObserver = new BossMngObserver(inventoryData);

        inventoryData.setCurrentData(10, 50);
    }
}
```
## 下载

**[观察者模式案例代码](https://github.com/eirunye/Oberser_Design)**

# 总结

观察者模式的让我们知道了在设计开发的时候一定要“多用组合，少用继承”。

我们设计开发是应该是针对接口变成，而不针对实现编程。

在java.util.*下的Observer和Observable可以实现观察者，但是Observable是一个类，这样我们是不违背了“多用组合少用继承”的OO编程思想，是的没错在java.util.Observable类违背了该规则。

# [我的GitHub博客](https://eirunye.github.io/)
### [设计模式](https://eirunye.github.io/categories/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F/)
大家可以进入这里学习**[设计模式](https://eirunye.github.io/categories/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F/)**