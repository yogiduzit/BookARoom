package com.example.bookaroom.data.database.entity;

public class Booking {
    private String userId;
    private String roomId;
    private Double startTime;
    private Double endTime;
    private String date;
    private String userName;
    private String id;
    private String buildingId;

    public Booking(){};

    public Booking(String id, String userID, String roomID, Double startTime, Double endTime, String date, String userName, String buildingId) {
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

    public Double getStartTime() {
        return startTime;
    }

    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    public Double getEndTime() {
        return endTime;
    }

    public void setEndTime(Double endTime) {
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

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }
}
