package com.example.baotoan.englishstories;

/**
 * Created by BaoToan on 10/29/2016.
 */

public class LessonItem {
    private int id;
    private String name;
    private int icon;
    private String url;

    public LessonItem(int id, String name, int icon, String url) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
