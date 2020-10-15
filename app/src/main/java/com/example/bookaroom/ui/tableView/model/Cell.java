package com.example.bookaroom.ui.tableView.model;

import androidx.annotation.NonNull;

import com.evrencoskun.tableview.sort.ISortableModel;

public class Cell {
    private String data;

    public Cell(@NonNull String oData) {
        this.data = oData;
    }

    @NonNull
    public String getData() {
        return data;
    }
}
