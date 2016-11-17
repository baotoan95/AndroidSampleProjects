package com.example.baotoan.wikicountry;

/**
 * Created by BaoToan on 10/31/2016.
 */

public class CountryModel {
    private String id;
    private String name;
    private String region;
    private String adminRegion;
    private String capitalCity;
    private String longitude;
    private String latitude;
    private String iso2Code;

    public CountryModel() {}

    public CountryModel(String id, String name, String region, String adminRegion, String capitalCity, String longitude, String latitude, String iso2Code) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.adminRegion = adminRegion;
        this.capitalCity = capitalCity;
        this.longitude = longitude;
        this.latitude = latitude;
        this.iso2Code = iso2Code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAdminRegion() {
        return adminRegion;
    }

    public void setAdminRegion(String adminRegion) {
        this.adminRegion = adminRegion;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public void setCapitalCity(String capitalCity) {
        this.capitalCity = capitalCity;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getIso2Code() {
        return iso2Code;
    }

    public void setIso2Code(String iso2Code) {
        this.iso2Code = iso2Code;
    }
}
