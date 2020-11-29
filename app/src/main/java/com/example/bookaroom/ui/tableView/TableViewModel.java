package com.example.bookaroom.ui.tableView;

import com.example.bookaroom.AdminPanel;
import com.example.bookaroom.data.database.entity.Bookable;
import com.example.bookaroom.data.database.entity.Booking;
import com.example.bookaroom.helpers.BookingHelper;
import com.example.bookaroom.ui.tableView.model.Cell;
import com.example.bookaroom.ui.tableView.model.ColumnHeader;
import com.example.bookaroom.ui.tableView.model.RowHeader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableViewModel {

    private List<RowHeader> rowHeaderList;
    private List<ColumnHeader> columnHeaderList;
    private List<List<Cell>> cellList;

    private List<String> bookingIntervals;

    private List<Bookable> bookables;
    private HashMap<String, List<Booking>> bookings;

    public TableViewModel(List<Bookable> bookables, HashMap<String, List<Booking>> bookings) {
        this.bookingIntervals =  BookingHelper.getBookingIntervals(AdminPanel.DAY_START_TIME, AdminPanel.DAY_END_TIME);
        this.bookables = bookables;
        this.bookings = bookings;

        rowHeaderList = this.createRowHeaderList();
        columnHeaderList = this.createColumnHeaderList();
        cellList = this.createCellList();
    }

    private List<ColumnHeader> createColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();
        for (Bookable bookable: bookables) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new ColumnHeader(bookable));
        }
        return list;
    }

    private List<List<Cell>> createCellList() {
        List<List<Cell>> lists = new ArrayList<>();

        for (int i = 0; i < rowHeaderList.size(); i++) {
            List<Cell> list = new ArrayList<>();
            String[] interval = rowHeaderList.get(i).getInterval().split("-");

            for (int j = 0; j < columnHeaderList.size(); j++) {
                String bookableName = columnHeaderList.get(j).getBookable().getId();
                int booked = BookingHelper.isIntervalBooked(interval[0], interval[1], bookings.get(bookableName));
                Cell cell = new Cell(interval[0], interval[1], bookableName, booked != 0);
                cell.setBookings(booked);
                list.add(cell);
            }
            // Add
            lists.add(list);
        }

        return lists;
    }

    private List<RowHeader> createRowHeaderList() {
        List<RowHeader> list = new ArrayList<>();

        for (String interval: bookingIntervals) {
            list.add(new RowHeader(interval));
        }
        return list;
    }

    public List<RowHeader> getRowHeaderList() {
        return rowHeaderList;
    }

    public List<ColumnHeader> getColumnHeaderList() {
        return columnHeaderList;
    }

    public List<List<Cell>> getCellList() {
        return cellList;
    }
}
