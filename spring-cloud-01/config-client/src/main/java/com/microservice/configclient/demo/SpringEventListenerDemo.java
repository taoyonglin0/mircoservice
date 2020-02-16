package com.microservice.configclient.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author taoyonglin
 * @version 1.0.0
 * @ClassName SpringEventListenerDemo.java
 * @Description Spring 自定义事件监听器
 * @createTime 2020年01月23日 17:15:00
 */
public class SpringEventListenerDemo {

    public static void main(String[] args) {
        ApplicationContext annotationContext = registerListener();

        annotationContext.publishEvent(new MyApplicationEvent(annotationContext,"消息1"));
        annotationContext.publishEvent(new MyApplicationEvent(annotationContext,"消息2"));
        annotationContext.publishEvent(new MyApplicationEvent(annotationContext,"消息3"));
    }

    /**
     * 注册Spring事件监听器
     * @return
     */
    public static ApplicationContext registerListener() {
        //获取Annotation 驱动上下文
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext();
        //注册监听器，根据自定义的事件，注册针对不同类型事件的监听器
        annotationContext.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            //监听器收到事件，处理事件
            @Override
            public void onApplicationEvent(MyApplicationEvent applicationEvent) {
                //处理事件
                Object source = applicationEvent.getSource();
                System.err.println("Spring自定义监听器监听到消息" + source.toString());
            }
        });
        //初始化容器
        annotationContext.refresh();
        return annotationContext;
    }

    /**
     * 自定义事件
     */
    public static class MyApplicationEvent extends ApplicationEvent {

        private static ApplicationContext context = null;

        MyApplicationEvent(ApplicationContext context,Object source) {
            super(source);
            this.context = context;
        }

        public MyApplicationEvent(Object source) {
            super(source);
        }
    }
}
