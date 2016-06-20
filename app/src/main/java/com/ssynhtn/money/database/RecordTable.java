package com.ssynhtn.money.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Garment on 2016/6/20.
 */ // 一笔转入或转出记录
public class RecordTable extends BaseTable implements BaseColumns {
    public static final String TABLE_NAME = "record";

    public static final String COL_RECORD_ID = _ID;
    public static final String COL_MONEY_BOOK_ID = "money_book_id";
    public static final String COL_MONEY = "money";
    public static final String COL_CONSUMPTION_TYPE_ID = "consumption_type_id";

    public static final String SQL_CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            COL_RECORD_ID + " integer primary key, " +
            COL_MONEY_BOOK_ID + " integer, " +
            COL_MONEY + " integer, " +
            COL_CONSUMPTION_TYPE_ID + " integer, " +
            "foreign key(" + COL_MONEY_BOOK_ID + ") references " + MoneyBookTable.TABLE_NAME + "(" + MoneyBookTable.COL_MONEY_BOOK_ID + ") on delete cascade, " +
            "foreign key(" + COL_CONSUMPTION_TYPE_ID + ") references " + ConsumptionTypeTable.TABLE_NAME + "(" + ConsumptionTypeTable.COL_CONSUMPTION_TYPE_ID + ") on delete set null" +
            ");";


    private static RecordTable sRecordTable;
    public static synchronized RecordTable getInstance() {
        if (sRecordTable == null) {
            sRecordTable = new RecordTable();
        }

        return sRecordTable;
    }

    public void createTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

}
