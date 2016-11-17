package com.example.baotoan.recyclerviewdemo;

/**
 * Created by BaoToan on 11/14/2016.
 */

public class Person {
    private String name;
    private boolean isMale;

    public Person() {
    }

    public Person(String name, boolean isMale) {
        this.name = name;
        this.isMale = isMale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
