package com.example.bookaroom.ui.tableView;

import com.example.bookaroom.ui.tableView.model.Cell;
import com.example.bookaroom.ui.tableView.model.ColumnHeader;
import com.example.bookaroom.ui.tableView.model.RowHeader;

import java.util.ArrayList;
import java.util.List;

public class TableViewModel {

    private List<RowHeader> rowHeaderList;
    private List<ColumnHeader> columnHeaderList;
    private List<List<Cell>> cellList;

    public TableViewModel() {
        rowHeaderList = this.createRowHeaderList(10);
        columnHeaderList = this.createColumnHeaderList();
        cellList = this.createCellList();
    }

    private List<ColumnHeader> createColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();

        // Create Column Headers
        list.add(new ColumnHeader("Id"));
        list.add(new ColumnHeader("Name"));
        list.add(new ColumnHeader("Nickname"));
        list.add(new ColumnHeader("Email"));
        list.add(new ColumnHeader("Birthday"));
        list.add(new ColumnHeader("Sex"));
        list.add(new ColumnHeader("Age"));

        return list;
    }

    private List<List<Cell>> createCellList() {
        List<List<Cell>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service

        for (int i = 0; i < 10; i++) {
            List<Cell> list = new ArrayList<>();

            // The order should be same with column header list;
            list.add(new Cell("1-" + i));          // "Id"
            list.add(new Cell("2-" + i));        // "Name"
            list.add(new Cell("3-" + i));    // "Nickname"
            list.add(new Cell("4-" + i));       // "Email"
            list.add(new Cell("5-" + i));   // "BirthDay"
            list.add(new Cell("6-" + i));      // "Gender"
            list.add(new Cell("7-" + i));         // "Age"

            // Add
            lists.add(list);
        }

        return lists;
    }

    private List<RowHeader> createRowHeaderList(int size) {
        List<RowHeader> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new RowHeader(String.valueOf(i + 1)));
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
