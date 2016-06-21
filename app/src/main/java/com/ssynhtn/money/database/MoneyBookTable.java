package com.ssynhtn.money.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Garment on 2016/6/20.
 */ // 一个账本
public class MoneyBookTable extends BaseTable implements BaseColumns {
    public static final String TABLE_NAME = "money_book";

    public static final String COL_MONEY_BOOK_ID = _ID;
    public static final String COL_MONEY_BOOK_NAME = "money_book_name";
    public static final String COL_CREATE_TIME = "create_time";

    public static final String SQL_CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            COL_MONEY_BOOK_ID + " integer primary key, " +
            COL_MONEY_BOOK_NAME + " text not null, " +
            COL_CREATE_TIME + " integer not null" +
            ");";

    private static MoneyBookTable sMoneyBookTable;
    public static synchronized MoneyBookTable getInstance() {
        if (sMoneyBookTable == null) {
            sMoneyBookTable = new MoneyBookTable();
        }

        return sMoneyBookTable;
    }

    public void createTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

}
