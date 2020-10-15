package com.example.bookaroom.data.database.entity;

public class Booking {

    public String roomId;
    public String time;

    public Booking( String sTime, String sRoomId) {
        this.time = sTime;
        this.roomId = sRoomId;
    }
}
