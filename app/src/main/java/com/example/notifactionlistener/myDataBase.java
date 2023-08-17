package com.example.notifactionlistener;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class myDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HistoryDatabase1.0";
    public static final String NOTIFICATION_LIST = "Notification_List";
    public static final String KEY_APP = "App_name";
    public static final String KEY_TITLE = "Title";
    public static final String KEY_DATE = "Date";
    public static final String KEY_TEXT = "Text";

    public myDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTransactionList = "CREATE TABLE " + NOTIFICATION_LIST + " ( " + KEY_APP + " TEXT, " + KEY_TITLE + " TEXT, " + KEY_TEXT + " TEXT, " + KEY_DATE + " TEXT)";
        db.execSQL(createTransactionList);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addItem(NotificationDetails item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_APP, item.getAppName());
        cv.put(KEY_TITLE, item.getTitle());
        cv.put(KEY_TEXT, item.getText());
        cv.put(KEY_DATE, item.getDate());

        db.insert(NOTIFICATION_LIST, null, cv);
    }

    public List<NotificationDetails> getData() {
        List<NotificationDetails> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + NOTIFICATION_LIST;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                NotificationDetails item = new NotificationDetails();
                item.setAppName(cursor.getString(0));
                item.setTitle(cursor.getString(1));
                item.setText(cursor.getString(2));
                item.setDate(cursor.getString(3));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

}
