# 什么是观察者模式？

观察者模式定义一系列对象之间的一对多关系，当一个对象改变、更新状态时，依赖它的都会收到通知改变或者更新。

## 一、自定义观察者模式

1.构造一个主题Subject或者是一个被观察者Observeable，这是一个接口
 接口方法有:
 注册观察者
 registerObserver(Observer o)
 解注册观察者
 unRegisterObserve(Observer o)
 更新数据
 notifyObservers();
 ```
   public interface Subject {

    void registerObserver(Observer observe);
    void unRegisterObserver(Observer observe);
    void notifyObservers();
  }
 ```
 
 2.构建一个被观察者实现改主题接口如本例的 DevelopmentProgressData.class
  在registerObserver(Observer o)方法中将观察者添加到注册列表中
  在 unRegisterObserve(Observer o)删除观察者
 ```
 public class DevelopmentProgressData implements Subject {
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
 }
 ```
 
3.构建一个观察者接口Observer
 方法：
 update(); //更新数据
```
 public interface Observer {

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

5.定义观察者，需实现Observes，DisplaySchedule(可忽略)接口，
<1>、将主题Subject设置为观察者的属性，并将其作为观察者的构造函数如 ProductManagerObserver.class
调用  developmentProgressSubject.registerObserver(this);将观察者注册到观察列表中
```
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

```
6.测试 RunTest.class


## 二、根据java.util.observerable包下的Observerable.class实现观察者模式功能

实现步骤：
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
 
  3.测试 Test.class
