package com.ssynhtn.money.database;

/**
 * Created by Garment on 2016/6/20.
 */
public class DatabaseHelper {
    private static DatabaseHelper sDatabaseHelper;

    public static synchronized DatabaseHelper getInstance() {
        if (sDatabaseHelper == null) {
            sDatabaseHelper = new DatabaseHelper();
        }

        return sDatabaseHelper;
    }


    public void addMoneyBook(String moneyBookName) {
    }



}
