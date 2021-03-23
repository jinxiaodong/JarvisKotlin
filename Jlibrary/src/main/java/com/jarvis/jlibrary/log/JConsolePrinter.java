package com.jarvis.jlibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
public class JConsolePrinter  implements  JLogPrinter{

    @Override
    public void print(@NonNull JLogConfig config, int level, String tag, @NonNull String printString) {
            int len = printString.length();
            int countOfSub = len/ JLogConfig.MAX_LEN;
            if(countOfSub >0) {
                int index = 0;
                for (int i = 0; i < countOfSub; i++) {
                    Log.println(level,tag,printString.substring(index,index +JLogConfig.MAX_LEN));
                    index += JLogConfig.MAX_LEN;
                }
                if(index!=len) {
                    Log.println(level,tag,printString.substring(index,len));
                }
            }else {
                Log.println(level,tag,printString);
            }

    }
}
