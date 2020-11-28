package com.example.bookaroom.ui.tableView.model;

public class Cell {
    private String startTime;
    private String endTime;
    private String bookable;

    public Cell(String startTime, String endTime, String bookable) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookable = bookable;
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
}
