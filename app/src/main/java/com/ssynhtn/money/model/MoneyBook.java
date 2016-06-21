package com.ssynhtn.money.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.ssynhtn.money.database.MoneyBookTable;

/**
 * Created by Garment on 2016/6/21.
 */
public class MoneyBook {
    public int mId;
    public String mName;
    public long mCreateTime;

    private MoneyBook() {
        super();
    }
    public MoneyBook(int id, String name, long createTime) {
        mId = id;
        mName = name;
        mCreateTime = createTime;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(MoneyBookTable.COL_MONEY_BOOK_ID, mId);
        values.put(MoneyBookTable.COL_MONEY_BOOK_NAME, mName);
        values.put(MoneyBookTable.COL_CREATE_TIME, mCreateTime);

        return values;
    }

    public static MoneyBook fromCursor(Cursor cursor) {
        MoneyBook moneyBook = new MoneyBook();

        moneyBook.mId = cursor.getInt(cursor.getColumnIndex(MoneyBookTable.COL_MONEY_BOOK_ID));
        moneyBook.mName = cursor.getString(cursor.getColumnIndex(MoneyBookTable.COL_MONEY_BOOK_NAME));
        moneyBook.mCreateTime = cursor.getLong(cursor.getColumnIndex(MoneyBookTable.COL_CREATE_TIME));

        return moneyBook;
    }


    @Override
    public String toString() {
        return "id: " + mId + ", name: " + mName + ", createTime: " + mCreateTime;
    }
}
