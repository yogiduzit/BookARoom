package com.example.bookaroom.ui.tableView;

import com.example.bookaroom.AdminPanel;
import com.example.bookaroom.data.database.entity.Bookable;
import com.example.bookaroom.helpers.DateHelper;
import com.example.bookaroom.ui.tableView.model.Cell;
import com.example.bookaroom.ui.tableView.model.ColumnHeader;
import com.example.bookaroom.ui.tableView.model.RowHeader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TableViewModel {

    private List<RowHeader> rowHeaderList;
    private List<ColumnHeader> columnHeaderList;
    private List<List<Cell>> cellList;

    private List<String> bookingIntervals;

    public TableViewModel(List<Bookable> bookables) {
        bookingIntervals =  DateHelper.getBookingIntervals(LocalDateTime.now().getHour(), AdminPanel.DAY_END_TIME);
        rowHeaderList = this.createRowHeaderList();
        columnHeaderList = this.createColumnHeaderList(bookables);
        cellList = this.createCellList();
    }

    private List<ColumnHeader> createColumnHeaderList(List<Bookable> bookables) {
        List<ColumnHeader> list = new ArrayList<>();
        for (Bookable bookable: bookables) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new ColumnHeader(bookable.getId()));
        }
        return list;
    }

    private List<List<Cell>> createCellList() {
        List<List<Cell>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service

        for (int i = 0; i < rowHeaderList.size(); i++) {
            List<Cell> list = new ArrayList<>();

            for (int j = 0; j < columnHeaderList.size(); j++) {
                list.add(new Cell(i + "-" + j));
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
