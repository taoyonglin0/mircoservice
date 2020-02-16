package com.microservice.configclient.demo;

import java.util.*;

/**
 * @author taoyonglin
 * @version 1.0.0
 * @ClassName ObserverDemo.java
 * @Description 发布订阅者模式demo
 * @createTime 2020年01月23日 17:01:00
 */
public class ObserverDemo {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        pushPattern(System.currentTimeMillis() + "");

        pullPattern(Arrays.asList("1","2","3","4","5"));
    }

    /**
     * 迭代器是拉模式
     * @param argList
     */
    public static void pullPattern(List<String> argList) {
        System.out.println("================拉模式 START=================");
        Iterator<String> iterator = argList.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        System.out.println("================拉模式 END=================");
    }

    /**
     * 发布订阅是推模式
     * @param msg
     */
    public static void pushPattern(String msg) {
        System.out.println("================推模式 START=================");
        //创建发布订阅管理对象
        MyObservable observable = new MyObservable();
        //添加订阅者
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.err.println("订阅者收到发布消息:" + arg.toString());
            }
        });
        //发布消息
        observable.setChanged();
        observable.notifyObservers(String.format("发布者在%s发布了一条休息:",msg));
        System.out.println("================推模式 END=================");
    }

    public static class MyObservable extends Observable {
        /**
         * 为了在测试demo中调用到该方法，因此继承修改修饰符protected->public
         * 进行标记发布者已经进行了修改
         */
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
}