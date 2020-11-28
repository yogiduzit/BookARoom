package com.example.bookaroom.ui.tableView.model;

import androidx.annotation.NonNull;

public class ColumnHeader {
    private String bookableName;

    public ColumnHeader(@NonNull String bookableName) {
        this.bookableName = bookableName;
    }

    public String getBookableName() {
        return bookableName;
    }

    public void setBookableName(String bookableName) {
        this.bookableName = bookableName;
    }
}
