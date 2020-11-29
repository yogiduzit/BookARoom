package com.example.bookaroom.ui.tableView.model;

import androidx.annotation.NonNull;

import com.example.bookaroom.data.database.entity.Bookable;

public class ColumnHeader {
    private Bookable bookable;

    public ColumnHeader(@NonNull Bookable bookable) {
        this.bookable = bookable;
    }

    public Bookable getBookable() {
        return bookable;
    }

    public void setBookable(Bookable bookable) {
        this.bookable = bookable;
    }
}
