package com.example.bookaroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.TableView;
import com.example.bookaroom.R;
import com.example.bookaroom.ui.adapter.CampusAdapter;
import com.example.bookaroom.ui.tableView.TableViewAdapter;
import com.example.bookaroom.ui.tableView.TableViewModel;

public class ChooseBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_booking);

        TableView bookingsTableView = findViewById(R.id.bookings_table_view);
        initTableView(bookingsTableView);
        setupHeadings();
    }

    public void onBookingSelected(View view) {
        Intent bookingDetails = new Intent(this, BookingForm.class);
        startActivity(bookingDetails);
    }

    private void setupHeadings() {
        TextView bookableName = findViewById(R.id.bookable_name);
        String campus = getIntent().getStringExtra(CampusAdapter.CAMPUS_NAME);
        String building = getIntent().getStringExtra(CampusAdapter.BUILDING_NAME);

        bookableName.setText(campus + " > " + building);
    }

    private void initTableView(TableView tableView) {
        TableViewAdapter adapter = new TableViewAdapter();
        tableView.setAdapter(adapter);
        TableViewModel viewModel = new TableViewModel();

        adapter.setAllItems(viewModel.getColumnHeaderList(), viewModel.getRowHeaderList(), viewModel.getCellList());
    }
}