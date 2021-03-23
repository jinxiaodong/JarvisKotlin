package com.jarvis.jlibrary.log;

import androidx.annotation.NonNull;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
public  interface  JLogPrinter {
    void print(@NonNull JLogConfig config ,int level,String tag,@NonNull String printString);
}
