package com.example.baotoan.sqlite;

/**
 * Created by BaoToan on 11/1/2016.
 */

public class NoteModel {
    private int id;
    private String note;
    private String datetime;

    public NoteModel() {}

    public NoteModel(int id, String note, String datetime) {
        this.id = id;
        this.note = note;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
