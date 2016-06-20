package com.ssynhtn.money.database;

/**
 * Created by Garment on 2016/6/20.
 */
public class BaseTable {
    public static String fullColumnName(String tableName, String colName) {
        return tableName + "." + colName;
    }
}
