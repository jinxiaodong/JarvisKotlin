package com.jarvis.kotlingrammar.arithmetic.swordoffer;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2/26/21
 */
public class SingletonDemo {

    public static void main(String[] args) {
        Integer integer3 = Integer.valueOf(129);
        int integer = 129;

        System.out.println(integer3 == integer);
    }
    private static class SingletonDemoHolder {
        private static SingletonDemo instance = new SingletonDemo();
    }

    private SingletonDemo() {
    }

    private static SingletonDemo getInstance() {
        return SingletonDemoHolder.instance;
    }

}
