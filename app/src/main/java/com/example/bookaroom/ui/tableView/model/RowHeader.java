package com.example.bookaroom.ui.tableView.model;

import androidx.annotation.NonNull;

public class RowHeader {
    String interval;

    public RowHeader(@NonNull String oData) {
        this.interval = oData;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
