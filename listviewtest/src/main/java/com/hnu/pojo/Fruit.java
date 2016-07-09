package com.hnu.pojo;

/**
 * Created by LJ on 2016-04-08.
 */
public class Fruit {

    private int resourceID;
    private String name;

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public Fruit(String name,int resourceID) {
        this.resourceID = resourceID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
