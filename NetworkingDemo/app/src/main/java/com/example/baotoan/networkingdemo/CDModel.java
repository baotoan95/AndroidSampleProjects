package com.example.baotoan.networkingdemo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by BaoToan on 10/30/2016.
 */

@Root(name = "CD")
public class CDModel {
    @Element(name = "TITLE")
    private String title;
    @Element(name = "ARTIST")
    private String artist;
    @Element(name = "COUNTRY")
    private String country;
    @Element(name = "COMPANY")
    private String company;
    @Element(name = "PRICE")
    private double price;
    @Element(name = "YEAR")
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
