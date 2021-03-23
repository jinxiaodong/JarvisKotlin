package com.jarvis.hencoder.generics;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 1/25/21
 */
public class NonGenericList {

    private Object[] instances = new Object[0];


    public Object get(int index) {
        return instances[index];
    }

    public void set(Object instance, int index) {
        this.instances[index] = instance;
    }

    public void add(Object newInstance) {
        instances = Arrays.copyOf(instances, instances.length + 1);
        instances[instances.length] = newInstance;
    }
}
