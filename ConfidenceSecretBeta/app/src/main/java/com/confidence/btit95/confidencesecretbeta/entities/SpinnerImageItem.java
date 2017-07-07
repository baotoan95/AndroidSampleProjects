package com.confidence.btit95.confidencesecretbeta.entities;

/**
 * Created by baotoan on 02/07/2017.
 */

public class SpinnerImageItem {
    private String name;
    private Integer image;

    public SpinnerImageItem(String name, Integer image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
