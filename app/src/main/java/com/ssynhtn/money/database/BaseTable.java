package com.ssynhtn.money.database;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garment on 2016/6/20.
 */
public class BaseTable {
    public static String fullColumnName(String tableName, String colName) {
        return tableName + "." + colName;
    }

    public String[] getFullColumns() {
        return getFullColumns(getClass());
    }

    public static String[] getFullColumns(Class<? extends BaseTable> clazz) {
        List<String> columns = new ArrayList<>();

        String tableName = getTableName(clazz);

        if (tableName == null) return new String[0];

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            int modifier = field.getModifiers();
            String name = field.getName();

            if (Modifier.isStatic(modifier) && Modifier.isFinal(modifier) && name.startsWith("COL")) {
                try {
                    columns.add(tableName + "." + field.get(null));
                } catch (IllegalAccessException ignored) {
                }
            }
        }

        return columns.toArray(new String[columns.size()]);
    }

    public static String getTableName(Class<? extends BaseTable> clazz) {

        try {
            Field field = clazz.getField("TABLE_NAME");

            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers) && field.getType() == String.class) {
                return (String) field.get(null);
            }
        } catch (Exception ignored) {
            // do nothing
        }

        return null;
    }


}
