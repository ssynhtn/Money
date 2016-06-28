package com.ssynhtn.money.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ssynhtn.money.BuildConfig;
import com.ssynhtn.money.MoneyApplication;

/**
 * Created by Garment on 2016/6/20.
 */

@SuppressWarnings("ConstantConditions")
public class MoneyProvider extends ContentProvider {
    public static final String AUTHORITY = BuildConfig.APPLICATION_ID;
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final Uri CONTENT_URI_MONEY_BOOKS = BASE_CONTENT_URI.buildUpon().appendEncodedPath(MoneyBookTable.TABLE_NAME).build();
    public static final Uri CONTENT_URI_RECORDS = BASE_CONTENT_URI.buildUpon().appendEncodedPath(RecordTable.TABLE_NAME).build();

    public static final int CODE_MONEY_BOOKS = 100;
    public static final int CODE_RECORDS = 101;

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(AUTHORITY, MoneyBookTable.TABLE_NAME, CODE_MONEY_BOOKS);
        sUriMatcher.addURI(AUTHORITY, RecordTable.TABLE_NAME, CODE_RECORDS);

    }


    private MoneyOpenHelper mMoneyOpenHelper;

    @Override
    public boolean onCreate() {
        mMoneyOpenHelper = MoneyOpenHelper.getInstance(getContext());
        return true;
    }

    public static void notifyUris() {
        MoneyApplication.getInstance().getContentResolver().notifyChange(BASE_CONTENT_URI, null);
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int code = sUriMatcher.match(uri);
        Cursor cursor = null;
        SQLiteDatabase db = mMoneyOpenHelper.getWritableDatabase();

        switch (code) {
            case CODE_MONEY_BOOKS: {
                cursor = db.query(MoneyBookTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }

            case CODE_RECORDS: {
                cursor = db.query(RecordTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }

            default: break;
        }


        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), BASE_CONTENT_URI);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
