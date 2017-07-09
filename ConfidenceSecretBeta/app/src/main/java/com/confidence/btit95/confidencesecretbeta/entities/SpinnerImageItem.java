package com.confidence.btit95.confidencesecretbeta.entities;

import java.util.Map;

/**
 * Created by baotoan on 02/07/2017.
 */

public class SpinnerImageItem {
    private String name;
    private Map<String, Integer> images;
    private String type;

    public SpinnerImageItem(String name, Map<String, Integer> images, String type) {
        this.name = name;
        this.images = images;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getImages() {
        return images;
    }

    public void setImages(Map<String, Integer> images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
