package com.example.bookaroom.data.database.entity;

import java.sql.Time;

public class Booking {
    public String id;
    public String roomId;
    public String startTime;
    public String endTime;
    public String date;
    public String userName;

    public Booking(){};

    public Booking(String id, String roomID, String startTime, String endTime, String date, String userName) {
        this.id = id;
        this.roomId = roomID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
