package com.example.bookaroom.data.database.entity;

public class Campus {
    private int campusNumber;
    private String id;

    public Campus(int number, String id) {
        this.campusNumber = number;
        this.id = id;
    }

    public int getCampusNumber() {
        return campusNumber;
    }

    public void setCampusNumber(int campusNumber) {
        this.campusNumber = campusNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
