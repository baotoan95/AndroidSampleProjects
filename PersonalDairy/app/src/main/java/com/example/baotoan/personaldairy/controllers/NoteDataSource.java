package com.example.baotoan.personaldairy.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.baotoan.personaldairy.models.NoteModel;

import java.util.ArrayList;

/**
 * Created by BaoToan on 11/4/2016.
 */

public class NoteDataSource {
    private NoteDataHelper noteDataHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    private final String[] columns = {NoteDataHelper.COL_ID, NoteDataHelper.COL_TITLE, NoteDataHelper.COL_STATUS,
            NoteDataHelper.COL_CONTENT, NoteDataHelper.COL_DATETIME, NoteDataHelper.COL_IMAGES};

    public NoteDataSource(Context context) {
        this.context = context;
        this.noteDataHelper = new NoteDataHelper(context);
    }

    public boolean open() {
        try {
            this.sqLiteDatabase = noteDataHelper.getWritableDatabase();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean close() {
        try {
            this.noteDataHelper.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<NoteModel> getAllNotes() {
        ArrayList<NoteModel> notes = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(NoteDataHelper.TABLE_NAME, columns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            NoteModel note = new NoteModel();
            note.setId(cursor.getInt(0));
            note.setTitle(cursor.getString(1));
            note.setStatus(cursor.getString(2));
            note.setContent(cursor.getString(3));
            note.setDatetime(cursor.getString(4));
            note.setImages(cursor.getString(5));
            notes.add(note);
            cursor.moveToNext();
        }
        return notes;
    }

    public void addNote(NoteModel note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDataHelper.COL_TITLE, note.getTitle());
        contentValues.put(NoteDataHelper.COL_STATUS, note.getStatus());
        contentValues.put(NoteDataHelper.COL_CONTENT, note.getContent());
        contentValues.put(NoteDataHelper.COL_DATETIME, note.getDatetime());
        contentValues.put(NoteDataHelper.COL_IMAGES, note.getImages());
        sqLiteDatabase.insert(NoteDataHelper.TABLE_NAME, null, contentValues);
        Toast.makeText(context, "Add success", Toast.LENGTH_SHORT).show();
    }

    public void deleteNote(int id) {
        sqLiteDatabase.delete(NoteDataHelper.TABLE_NAME, NoteDataHelper.COL_ID + "=?", new String[] {String.valueOf(id)});
        Toast.makeText(context, "Delete success", Toast.LENGTH_SHORT).show();
    }

    public void updateNote(NoteModel note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteDataHelper.COL_TITLE, note.getTitle());
        contentValues.put(NoteDataHelper.COL_STATUS, note.getStatus());
        contentValues.put(NoteDataHelper.COL_CONTENT, note.getContent());
        contentValues.put(NoteDataHelper.COL_IMAGES, note.getImages());
        sqLiteDatabase.update(NoteDataHelper.TABLE_NAME, contentValues, NoteDataHelper.COL_ID + "=?", new String[] {String.valueOf(note.getId())});
        Toast.makeText(context, "Update success", Toast.LENGTH_SHORT).show();
    }
}
