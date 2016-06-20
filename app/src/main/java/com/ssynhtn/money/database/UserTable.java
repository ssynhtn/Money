package com.ssynhtn.money.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Garment on 2016/6/20.
 */
public class UserTable extends BaseTable implements BaseColumns {
    public static final String TABLE_NAME = "user";

    public static final String COL_USER_ID = _ID;
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_CREATE_TIME = "create_time";


    public static final String SQL_CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            COL_USER_ID + " integer primary key, " +
            COL_USER_NAME + " text not null, " +
            COL_CREATE_TIME + " integer not null" +
            ");";


    private static UserTable sUserTable;
    public static synchronized UserTable getInstance() {
        if (sUserTable == null) {
            sUserTable = new UserTable();
        }

        return sUserTable;
    }


    public void createTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);

    }

}
