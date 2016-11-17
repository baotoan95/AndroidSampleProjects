package com.example.baotoan.personaldairy.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BaoToan on 11/4/2016.
 */

public class NoteDataHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "personaldiary.db";
    public static final String TABLE_NAME = "notes";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_STATUS = "status";
    public static final String COL_CONTENT = "content";
    public static final String COL_DATETIME = "datetime";
    public static final String COL_IMAGES = "images";

    public static int DB_VERSION = 1;

    public static final String CREATE_DB = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY autoincrement, "
            + COL_TITLE + " TEXT not null, "
            + COL_STATUS + " TEXT, "
            + COL_CONTENT + " TEXT, "
            + COL_DATETIME + " TEXT, "
            + COL_IMAGES + " TEXT" +
            ")";

    public NoteDataHelper(Context context) {
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
