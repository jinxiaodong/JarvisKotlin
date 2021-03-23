package com.jarvis.jlibrary.log;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/22/21
 */
public class JDisplayUtil {


    public static int getDisplayWidthInPx(@NonNull Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display defaultDisplay = wm.getDefaultDisplay();
            Point size = new Point();
            defaultDisplay.getSize(size);
            return size.x;
        }
        return 0;
    }

    public static int getDisplayHeightInPx(@NonNull Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display defaultDisplay = wm.getDefaultDisplay();
            Point size = new Point();
            defaultDisplay.getSize(size);
            return size.y;
        }
        return 0;
    }

}
