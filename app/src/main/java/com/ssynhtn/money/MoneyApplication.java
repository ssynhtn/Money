package com.ssynhtn.money;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Garment on 2016/6/20.
 */
public class MoneyApplication extends Application {

    private static MoneyApplication sMoneyApplication;

    public MoneyApplication() {
        sMoneyApplication = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);
    }

    public static synchronized MoneyApplication getInstance() {
        return sMoneyApplication;
    }
}
