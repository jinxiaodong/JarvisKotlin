package com.jarvis.jlibrary.log;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
public abstract class JLogConfig {

     static int MAX_LEN = 512;

    static JStackTraceFormatter jStackTraceFormatter = new JStackTraceFormatter();
    static JThreadFormatter jThreadFormatter = new JThreadFormatter();

    public  JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "JLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public JLogPrinter[] printers(){
        return  null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }
}
