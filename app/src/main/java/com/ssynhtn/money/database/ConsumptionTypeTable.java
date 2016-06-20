package com.ssynhtn.money.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Garment on 2016/6/20.
 */ // 消费类别
public class ConsumptionTypeTable extends BaseTable implements BaseColumns {
    public static final String TABLE_NAME = "consumption_type";

    public static final String COL_CONSUMPTION_TYPE_ID = _ID;
    public static final String COL_CONSUMPTION_TYPE_NAME = "consumption_type_name";

    public static final String SQL_CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            COL_CONSUMPTION_TYPE_ID + " integer primary key, " +
            COL_CONSUMPTION_TYPE_NAME + " text not null" +
            ");";

    private static ConsumptionTypeTable sConsumptionTypeTable;
    public static synchronized ConsumptionTypeTable getInstance() {
        if (sConsumptionTypeTable == null) {
            sConsumptionTypeTable = new ConsumptionTypeTable();
        }

        return sConsumptionTypeTable;
    }

    public void createTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

}
