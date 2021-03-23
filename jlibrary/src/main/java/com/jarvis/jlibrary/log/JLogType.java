package com.jarvis.jlibrary.log;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/18/21
 */
public class JLogType {

    @IntDef({V,D,I,W,E,A})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    public static final int V = Log.VERBOSE;
    public static final int D = Log.DEBUG;
    public static final int I = Log.INFO;
    public static final int W = Log.WARN;
    public static final int E = Log.ERROR;
    public static final int A = Log.ASSERT;
}
