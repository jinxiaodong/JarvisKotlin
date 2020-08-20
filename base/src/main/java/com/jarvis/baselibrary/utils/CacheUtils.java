package com.jarvis.baselibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.jarvis.baselibrary.BaseApplication;
import com.jarvis.baselibrary.R;


public class CacheUtils {
    @SuppressLint("StaticFieldLeak")
    private static Context context = BaseApplication.currentApplication();

    private static SharedPreferences SP = context.getSharedPreferences(context.getString(R.string.base_app_name), Context.MODE_PRIVATE);

    public static void save(String key, String value) {
        SP.edit().putString(key, value).apply();
    }

    public static String get(String key) {
        return SP.getString(key, null);
    }
}
