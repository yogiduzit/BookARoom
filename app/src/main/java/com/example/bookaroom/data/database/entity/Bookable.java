package com.example.bookaroom.data.database.entity;
import org.apache.commons.text.WordUtils;


public class Bookable {
    private String id;
    private String building;
    private int capacity;
    private String type;
    private String[] features;

    public Bookable(String id, String building, int capacity, String type, String[] features) {
        this.id = WordUtils.capitalize(id);
        this.building = building;
        this.capacity = capacity;
        this.type = type;
        this.features = features;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = WordUtils.capitalize(id);
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getFeatures() {
        return features;
    }

    public void setFeatures(String[] features) {
        this.features = features;
    }
}
