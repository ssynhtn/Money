package com.ssynhtn.money.utils;

import android.util.Log;

/**
 * Created by Garment on 2016/6/28.
 */
public class MyLog {
    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable e) {
        Log.d(tag, msg, e);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        Log.e(tag, msg, e);
    }

}
