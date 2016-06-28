package com.ssynhtn.money.model;

import android.database.Cursor;

import com.ssynhtn.money.database.RecordTable;
import com.ssynhtn.money.database.UserTable;

/**
 * Created by Garment on 2016/6/28.
 */
public class Record {
    public int mId;
    public int mMoneyBookId;
    public int mMoney;
    public int mConsumptionTypeId;
    public int mOwnerId;
    public long mCreateTime;

    public static Record fromCursor(Cursor cursor) {
        Record record = new Record();

        record.mId = cursor.getInt(cursor.getColumnIndex(RecordTable.COL_RECORD_ID));
        record.mMoneyBookId = cursor.getInt(cursor.getColumnIndex(RecordTable.COL_MONEY_BOOK_ID));
        record.mMoney = cursor.getInt(cursor.getColumnIndex(RecordTable.COL_MONEY));
        record.mConsumptionTypeId = cursor.getInt(cursor.getColumnIndex(RecordTable.COL_CONSUMPTION_TYPE_ID));
        record.mOwnerId = cursor.getInt(cursor.getColumnIndex(UserTable.COL_USER_ID));
        record.mCreateTime = cursor.getLong(cursor.getColumnIndex(RecordTable.COL_CREATE_TIME));

        return record;
    }


}
