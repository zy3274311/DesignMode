package com.github.zy3274311.designmode;

public class Singleton {
    private static final Singleton hungrySingleton = new Singleton();
    //饿汉式
    public static Singleton createHungry(){
        return hungrySingleton;
    }

    //instance = new Singleton()这句，这并非是一个原子操作， 将 instance 变量声明成 volatile 就可以了
    private volatile static Singleton singleton = null;

    private Singleton(){

    }

    //懒汉式 线程不安全 错误写法
    public static Singleton createLazyUnSafe(){
        if(singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }

    //懒汉式 线程安全
    public static synchronized Singleton createLazySafe(){
        if(singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }

    //双重检验锁
    public static Singleton createDoubleCheck(){
        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    //静态内部类
    private static class SingletonHolder {
        private static final Singleton singleton = new Singleton();
    }
    public static Singleton createStaticInnerClass(){
        return SingletonHolder.singleton;
    }

    //枚举
    public enum SingletonEnum {
        singleton_enum;

        Singleton singleton = new Singleton();
    }
    public static Singleton createEmum(){
        return SingletonEnum.singleton_enum.singleton;
    }
}
