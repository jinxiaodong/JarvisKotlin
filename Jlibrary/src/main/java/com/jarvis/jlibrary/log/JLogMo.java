package com.jarvis.jlibrary.log;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
public class JLogMo {


    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);

    public long timeMillis;

    public int level;

    public String tag;

    public String log;


    public JLogMo(long timeMillis, int level, String tag, String log) {
        this.timeMillis = timeMillis;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    public String getFlattenedLog(){
        return  getFlattened()+"\n"+log;
    }


    public String getFlattened() {
        return format(timeMillis) + '|' + level + "|" + tag + "|:";
    }

    private String format(long timeMillis) {
        return sdf.format(timeMillis);
    }
}
