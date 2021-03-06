package com.example.baotoan.gridviewdemo;

/**
 * Created by BaoToan on 10/29/2016.
 */

public class ContactModel {
    private String name;
    private String phone;
    private int imageId;

    public ContactModel(int imageId, String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
