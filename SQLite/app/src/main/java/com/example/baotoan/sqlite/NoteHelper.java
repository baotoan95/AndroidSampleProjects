package com.example.baotoan.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BaoToan on 11/1/2016.
 * Helper create db
 */

public class NoteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mynotes.db";
    public static final String TABLE_NAME = "notes";
    public static final String COL_ID = "_id";
    public static final String COL_NOTE = "note";
    public static final String COL_DATE = "datetime";
    public static final int DB_VERSION = 1;
    public static final String CREATE_DB = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY autoincrement, "
            + COL_NOTE + " TEXT not null, "
            + COL_DATE + " TEXT" +
    ")";

    public NoteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }
}
