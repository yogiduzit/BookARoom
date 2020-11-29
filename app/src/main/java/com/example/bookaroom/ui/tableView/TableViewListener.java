package com.example.bookaroom.ui.tableView;

import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.evrencoskun.tableview.listener.ITableViewListener;
import com.example.bookaroom.AdminPanel;
import com.example.bookaroom.R;
import com.example.bookaroom.helpers.ToastHelper;
import com.example.bookaroom.ui.BookingForm;
import com.skydoves.balloon.ArrowConstraints;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

public class TableViewListener implements ITableViewListener {

    String buildingId;

    public TableViewListener(String buildingId) {
        this.buildingId = buildingId;
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        /*
        It would be nice to have drag and drop but the library doesn't support it and I don't have enough time to
        delve into the implementation. It would involve using MotionEvent API to differentiate between a normal tap
        and a drag event.
        Another option is to make the user click on the intervals they want to book, but that would involve extra constraints
        such as you cannot book the same interval for two different rooms.
        So, in order to keep it simple, the user can simply click on one of the 30 minute intervals and change it later
        when they enter the booking form
         */
        if (cellView != null && cellView instanceof TableViewAdapter.CellViewHolder) {
            if (((TableViewAdapter.CellViewHolder) cellView).getCell().getBookings() == AdminPanel.MAX_BOOKINGS_PER_ROOM) {
                return;
            }
            Intent intent = new Intent(cellView.itemView.getContext(), BookingForm.class);
            intent.putExtra("bookable", ((TableViewAdapter.CellViewHolder) cellView).getCell().getBookable());
            intent.putExtra("startTime", ((TableViewAdapter.CellViewHolder) cellView).getCell().getStartTime());
            intent.putExtra("endTime", ((TableViewAdapter.CellViewHolder) cellView).getCell().getEndTime());
            intent.putExtra("building", buildingId);
            cellView.itemView.getContext().startActivity(intent);
        }
    }

    @Override
    public void onCellDoubleClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        return;
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        if (cellView != null && cellView instanceof TableViewAdapter.CellViewHolder) {
            Balloon.Builder builder = new Balloon.Builder(cellView.itemView.getContext())
                    .setArrowSize(10)
                    .setArrowOrientation(ArrowOrientation.BOTTOM)
                    .setArrowConstraints(ArrowConstraints.ALIGN_ANCHOR)
                    .setArrowPosition(0.5f)
                    .setArrowVisible(true)
                    .setCornerRadius(4f)
                    .setAlpha(0.9f)
                    .setPadding(2)
                    .setBalloonAnimation(BalloonAnimation.FADE);
            if (((TableViewAdapter.CellViewHolder) cellView).getCell().getBookings() == AdminPanel.MAX_BOOKINGS_PER_ROOM) {
                builder.setText("This book is completely booked for this time interval");
            } else {
                builder.setText("Available bookings: " + (AdminPanel.MAX_BOOKINGS_PER_ROOM - ((TableViewAdapter.CellViewHolder) cellView).getCell().getBookings()));
            }
                builder.build().show(cellView.itemView);
        }
    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {
        if (columnHeaderView != null && columnHeaderView instanceof TableViewAdapter.ColumnHeaderViewHolder) {
            Balloon balloon = new Balloon.Builder(columnHeaderView.itemView.getContext())
                    .setLayout(R.layout.layout_bookable_info)
                    .setArrowSize(10)
                    .setArrowOrientation(ArrowOrientation.BOTTOM)
                    .setArrowConstraints(ArrowConstraints.ALIGN_ANCHOR)
                    .setArrowPosition(0.5f)
                    .setArrowVisible(true)
                    .setCornerRadius(4f)
                    .setAlpha(0.9f)
                    .setBalloonAnimation(BalloonAnimation.FADE)
                    .build();
            TextView name = balloon.getContentView().findViewById(R.id.bookable_title);
            TextView capacity = balloon.getContentView().findViewById(R.id.bookable_capacity);
            TextView type = balloon.getContentView().findViewById(R.id.bookable_type);
            TextView features = balloon.getContentView().findViewById(R.id.bookable_features);

            String featureList = "Features: \n" + String.join("\n", ((TableViewAdapter.ColumnHeaderViewHolder) columnHeaderView).getColumnHeader().getBookable().getFeatures());

            name.setText("Name: " + ((TableViewAdapter.ColumnHeaderViewHolder) columnHeaderView).getColumnHeader().getBookable().getId());
            capacity.setText("Capacity: " + ((TableViewAdapter.ColumnHeaderViewHolder) columnHeaderView).getColumnHeader().getBookable().getCapacity());
            type.setText("Type: " + ((TableViewAdapter.ColumnHeaderViewHolder) columnHeaderView).getColumnHeader().getBookable().getType());
            features.setText(featureList);
            balloon.show(columnHeaderView.itemView);
        }

    }

    @Override
    public void onColumnHeaderDoubleClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {
        return;
    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {
        return;
    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        return;
    }

    @Override
    public void onRowHeaderDoubleClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        return;
    }
}
