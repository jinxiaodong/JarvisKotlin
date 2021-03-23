package com.jarvis.jlibrary.log;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
public class JThreadFormatter  implements  JLogFromatter<Thread>{
    @Override
    public String format(Thread data) {
        return "Thread:" +data.getName();
    }
}
