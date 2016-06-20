package com.ssynhtn.money;

import android.app.Application;

/**
 * Created by Garment on 2016/6/20.
 */
public class MoneyApplication extends Application {

    private static MoneyApplication sMoneyApplication;

    public MoneyApplication() {
        sMoneyApplication = this;
    }

    public synchronized MoneyApplication getInstance() {
        return sMoneyApplication;
    }
}
