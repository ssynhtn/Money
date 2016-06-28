package com.ssynhtn.money.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.ssynhtn.money.MoneyApplication;
import com.ssynhtn.money.model.Record;

/**
 * Created by Garment on 2016/6/20.
 */
public class DatabaseHelper {
    private static DatabaseHelper sDatabaseHelper;

    private MoneyOpenHelper mMoneyOpenHelper;

    public static synchronized DatabaseHelper getInstance() {
        if (sDatabaseHelper == null) {
            sDatabaseHelper = new DatabaseHelper();
        }

        return sDatabaseHelper;
    }

    public DatabaseHelper() {
        mMoneyOpenHelper = MoneyOpenHelper.getInstance(MoneyApplication.getInstance());
    }



    public void addMoneyBook(String moneyBookName) {
        SQLiteDatabase db = mMoneyOpenHelper.getWritableDatabase();

        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();
            values.put(MoneyBookTable.COL_MONEY_BOOK_NAME, moneyBookName);
            values.put(MoneyBookTable.COL_CREATE_TIME, System.currentTimeMillis());

            db.insertOrThrow(MoneyBookTable.TABLE_NAME, null, values);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            MoneyProvider.notifyUris();
        }
    }

    public void addRecord(Record record) {

    }



}
