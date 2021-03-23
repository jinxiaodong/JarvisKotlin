package com.jarvis.jlibrary.log;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
public interface JLogFromatter<T> {

    String format(T data);
}
