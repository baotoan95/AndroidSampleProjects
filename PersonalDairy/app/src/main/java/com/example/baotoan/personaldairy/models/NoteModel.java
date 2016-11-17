package com.example.baotoan.personaldairy.models;

import java.util.ArrayList;

/**
 * Created by BaoToan on 11/3/2016.
 */

public class NoteModel {
    public static final String COL_HAPPY = "#ff0000";
    public static final String COL_LUCKY = "#ff0000";
    public static final String COL_SAD = "#ff0000";
    public static final String COL_TIRED = "#ff0000";
    public static final String COL_ANGRY = "#ff0000";

    private int id;
    private String title;
    private String status;
    private String content;
    private String datetime;
    private String images;

    public NoteModel() {}

    public NoteModel(int id, String title, String status, String content, String datetime, String images) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.content = content;
        this.datetime = datetime;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
