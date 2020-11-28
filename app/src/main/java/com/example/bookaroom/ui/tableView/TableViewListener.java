package com.example.bookaroom.ui.tableView;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evrencoskun.tableview.listener.ITableViewListener;
import com.example.bookaroom.ui.BookingForm;

public class TableViewListener implements ITableViewListener {

    String buildingId;

    public TableViewListener(String buildingId) {
        this.buildingId = buildingId;
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        if (cellView != null && cellView instanceof TableViewAdapter.CellViewHolder) {
            Intent intent = new Intent(cellView.itemView.getContext(), BookingForm.class);
            intent.putExtra("bookable", ((TableViewAdapter.CellViewHolder) cellView).bookableName);
            intent.putExtra("startTime", ((TableViewAdapter.CellViewHolder) cellView).startTime);
            intent.putExtra("endTime", ((TableViewAdapter.CellViewHolder) cellView).endTime);
            intent.putExtra("building", buildingId);
            cellView.itemView.getContext().startActivity(intent);
        }
    }

    @Override
    public void onCellDoubleClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onColumnHeaderDoubleClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void onRowHeaderDoubleClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }
}
