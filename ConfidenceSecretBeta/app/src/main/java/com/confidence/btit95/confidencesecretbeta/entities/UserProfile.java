package com.confidence.btit95.confidencesecretbeta.entities;

import java.io.Serializable;

/**
 * Created by baotoan on 09/07/2017.
 */

public class UserProfile implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String shortBio;
    private String email;
    private String phone;
    private String avatar;
    private String fullName;

    public UserProfile() {}

    public UserProfile(int id, String firstName, String lastName, String shortBio, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shortBio = shortBio;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        if(fullName != null && fullName.trim().length() > 0) {
            return fullName;
        }
        return firstName + " " + lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
