package com.example.bookaroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.evrencoskun.tableview.TableView;
import com.example.bookaroom.R;
import com.example.bookaroom.ui.tableView.TableViewAdapter;
import com.example.bookaroom.ui.tableView.TableViewModel;

public class ChooseBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_booking);
        getSupportActionBar().hide();

        TableView bookingsTableView = findViewById(R.id.bookings_table_view);
        initTableView(bookingsTableView);
    }

    private void initTableView(TableView tableView) {
        TableViewAdapter adapter = new TableViewAdapter();
        tableView.setAdapter(adapter);
        TableViewModel viewModel = new TableViewModel();

        adapter.setAllItems(viewModel.getColumnHeaderList(), viewModel.getRowHeaderList(), viewModel.getCellList());
    }

    public void onBookingSelected(View view) {
        Intent bookingForm = new Intent(this, BookingForm.class);
        startActivity(bookingForm);
    }
}