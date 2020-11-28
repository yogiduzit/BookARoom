package com.example.bookaroom.data.database.entity;

public class Booking {
    public String userId;
    public String roomId;
    public String startTime;
    public String endTime;
    public String date;
    public String userName;
    public String id;
    public String buildingId;

    public Booking(){};

    public Booking(String id, String userID, String roomID, String startTime, String endTime, String date, String userName, String buildingId) {
        this.id = id;
        this.userId = userID; //user id from google login
        this.roomId = roomID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.userName = userName;
        this.buildingId = buildingId;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userID) {
        this.userId = userID;
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
