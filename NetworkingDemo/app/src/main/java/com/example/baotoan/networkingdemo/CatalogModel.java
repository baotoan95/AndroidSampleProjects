package com.example.baotoan.networkingdemo;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by BaoToan on 10/30/2016.
 */

@Root
public class CatalogModel {
    @ElementList(entry = "CD", inline = true)
    private ArrayList<CDModel> cds;

    public ArrayList<CDModel> getCds() {
        return cds;
    }

    public void setCds(ArrayList<CDModel> cds) {
        this.cds = cds;
    }
}
