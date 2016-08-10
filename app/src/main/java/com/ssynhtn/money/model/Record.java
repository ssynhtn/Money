package com.ssynhtn.money.model;

import android.database.Cursor;

import com.ssynhtn.money.database.RecordTable;
import com.ssynhtn.money.database.UserTable;

import org.joda.time.DateTime;

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

    public int getYear() {
        return 0;
    }
    public int getMonth() {
        return 0;
    }
    public int getDay() {
        return 0;
    }
    public int getHour() {
        return 0;
    }
    public int getMinute() {
        return 0;
    }

    public void setDate(int year, int month, int day) {
        DateTime dateTime = new DateTime(mCreateTime);
        dateTime = dateTime.withDate(year, month + 1, day);     // joda time 中 month 从 1 开始, java time 中 month 从 0 开始
        mCreateTime = dateTime.getMillis();
    }

    public void setTime(int hour, int minute) {
        DateTime dateTime = new DateTime(mCreateTime);
        dateTime = dateTime.withTime(hour, minute, 0, 0);
        mCreateTime = dateTime.getMillis();
    }


    public static Record newInstance(MoneyBook moneyBook) {
        Record record = new Record();
        record.mMoneyBookId = moneyBook.mId;
        record.mCreateTime = new DateTime().getMillis();

        return record;
    }
}
