package com.confidence.btit95.confidencesecretbeta.entities;

/**
 * Created by baotoan on 06/07/2017.
 */

public class SpinnerLangItem {
    private String name;
    private String code;
    private Integer image;

    public SpinnerLangItem(String name, String code, Integer image) {
        this.name = name;
        this.code = code;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
