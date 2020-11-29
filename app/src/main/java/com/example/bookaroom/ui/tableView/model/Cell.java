package com.example.bookaroom.ui.tableView.model;

public class Cell {
    private String startTime;
    private String endTime;
    private String bookable;
    private boolean booked;
    private int bookings;

    public Cell(String startTime, String endTime, String bookable, boolean booked) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookable = bookable;
        this.booked = booked;
        this.bookings = 0;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getBookable() {
        return bookable;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setBookable(String bookable) {
        this.bookable = bookable;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getBookings() {
        return bookings;
    }

    public void setBookings(int bookings) {
        this.bookings = bookings;
    }
}
