package com.example.bookaroom.data.database.entity;

public class Bookable {
    private String id;
    private String building;

    public Bookable(String id, String building) {
        this.id = id;
        this.building = building;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
