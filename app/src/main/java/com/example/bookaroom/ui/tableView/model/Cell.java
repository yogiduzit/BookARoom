package com.example.bookaroom.ui.tableView.model;

import androidx.annotation.Nullable;

import com.evrencoskun.tableview.sort.ISortableModel;

public class Cell {
    private String data;

    public Cell(@Nullable String oData) {
        this.data = oData;
    }

    @Nullable
    public String getData() {
        return data;
    }
}
