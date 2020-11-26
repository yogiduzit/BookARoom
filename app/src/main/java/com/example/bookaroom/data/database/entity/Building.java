package com.example.bookaroom.data.database.entity;

import java.util.ArrayList;

public class Building {
    private String campus;
    private String id;
    private String name;

    public Building(String campus, String id, String name) {
        this.campus = campus;
        this.id = id;
        this.name = name;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
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

    @Override
    public String toString() {
        return "Building{" +
                "campus='" + campus + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
