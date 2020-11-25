package com.example.bookaroom.ui.tableView;

import com.example.bookaroom.AdminPanel;
import com.example.bookaroom.helpers.DateHelper;
import com.example.bookaroom.ui.tableView.model.Cell;
import com.example.bookaroom.ui.tableView.model.ColumnHeader;
import com.example.bookaroom.ui.tableView.model.RowHeader;

import java.util.ArrayList;
import java.util.List;

public class TableViewModel {

    private List<RowHeader> rowHeaderList;
    private List<ColumnHeader> columnHeaderList;
    private List<List<Cell>> cellList;

    private List<String> bookingIntervals;

    public TableViewModel() {
        bookingIntervals =  DateHelper.getBookingIntervals(AdminPanel.DAY_START_TIME, AdminPanel.DAY_END_TIME);
        rowHeaderList = this.createRowHeaderList();
        columnHeaderList = this.createColumnHeaderList(10);
        cellList = this.createCellList();
    }

    private List<ColumnHeader> createColumnHeaderList(int size) {
        List<ColumnHeader> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new ColumnHeader("Room " + String.valueOf(i + 1)));
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
