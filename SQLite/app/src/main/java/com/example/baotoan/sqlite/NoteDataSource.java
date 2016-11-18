package com.example.baotoan.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by BaoToan on 11/1/2016.
 */

public class NoteDataSource {
    private NoteHelper noteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String[] allCols = {NoteHelper.COL_ID, NoteHelper.COL_NOTE, NoteHelper.COL_DATE};

    private Context context;

    public NoteDataSource(Context context) {
        this.context = context;
        this.noteHelper = new NoteHelper(context);
    }

    public boolean open() {
        try {
            sqLiteDatabase = noteHelper.getWritableDatabase();
            return true;
        } catch (SQLiteException e) {
            return false;
        }
    }

    public boolean close() {
        try {
            noteHelper.close();
            return true;
        } catch (SQLiteException e) {
            return false;
        }
    }

    public void addNewNote(String note) {
        String datetime = DateFormat.getDateTimeInstance().format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteHelper.COL_NOTE, note);
        contentValues.put(NoteHelper.COL_DATE, datetime);
        sqLiteDatabase.insert(NoteHelper.TABLE_NAME, null, contentValues);
        Toast.makeText(context, "Add note success", Toast.LENGTH_SHORT).show();
    }

    public void deleteNote(int id) {
        sqLiteDatabase.delete(NoteHelper.TABLE_NAME, NoteHelper.COL_ID + "=?", new String[] {String.valueOf(id)});
        Toast.makeText(context, "Delete note success", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<NoteModel> getAll() {
        ArrayList<NoteModel> arrNotes = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(NoteHelper.TABLE_NAME, allCols, null, null, null, null, null);
        if(null == cursor) {
            return null;
        }

        cursor.moveToFirst();

        // loop to get row data
        while(!cursor.isAfterLast()) {
            NoteModel note = new NoteModel();
            note.setId(cursor.getInt(0));
            note.setNote(cursor.getString(1));
            note.setDatetime(cursor.getString(2));
            arrNotes.add(note);
            // move to next note
            cursor.moveToNext();
        }

        return arrNotes;
    }
}
