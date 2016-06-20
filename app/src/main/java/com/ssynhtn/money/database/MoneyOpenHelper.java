package com.ssynhtn.money.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Garment on 2016/6/20.
 */
public class MoneyOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "money.db";
    private static final int DB_VERSION = 1;

    private static MoneyOpenHelper sMoneyOpenHelper;

    public static synchronized MoneyOpenHelper getInstance(Context context) {
        if (sMoneyOpenHelper == null) {
            sMoneyOpenHelper = new MoneyOpenHelper(context.getApplicationContext());
        }

        return sMoneyOpenHelper;
    }


    public MoneyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        UserTable.getInstance().createTable(db);
        MoneyBookTable.getInstance().createTable(db);
        ConsumptionTypeTable.getInstance().createTable(db);
        RecordTable.getInstance().createTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
