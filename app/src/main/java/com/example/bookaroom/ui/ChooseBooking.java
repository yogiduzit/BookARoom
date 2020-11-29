package com.example.bookaroom.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evrencoskun.tableview.TableView;
import com.example.bookaroom.R;
import com.example.bookaroom.data.database.entity.Bookable;
import com.example.bookaroom.data.database.entity.Booking;
import com.example.bookaroom.ui.adapter.CampusAdapter;
import com.example.bookaroom.ui.tableView.TableViewAdapter;
import com.example.bookaroom.ui.tableView.TableViewListener;
import com.example.bookaroom.ui.tableView.TableViewModel;
import com.example.bookaroom.ui.viewModel.BookablesViewModel;
import com.example.bookaroom.ui.viewModel.BookingsViewModel;

import org.apache.commons.text.WordUtils;

import java.util.HashMap;
import java.util.List;

public class ChooseBooking extends AppCompatActivity {

    private String campusId;
    private String buildingId;

    private TableView bookingsTableView;
    private Button bookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_booking);

        campusId = getIntent().getStringExtra(CampusAdapter.CAMPUS_NAME);
        buildingId = getIntent().getStringExtra(CampusAdapter.BUILDING_NAME);

        bookingsTableView = findViewById(R.id.bookings_table_view);
        bookBtn = findViewById(R.id.book_button);

        BookablesViewModel bookablesViewModel = new ViewModelProvider(this).get(BookablesViewModel.class);
        BookingsViewModel bookingsViewModel = new ViewModelProvider(this).get(BookingsViewModel.class);
        bookablesViewModel.getBookables(campusId, buildingId).observe(this, bookables -> {
            if (bookables.isEmpty()) {
                bookingsTableView.setVisibility(View.GONE);
                return;
            }
            bookingsViewModel.getBookings(buildingId).observe(this, bookings -> {
                initTableView(bookingsTableView, bookables, bookings);
            });
        });

        setupHeadings(campusId, buildingId);
    }

    private void setupHeadings(String campus, String building) {
        Button bookableName = findViewById(R.id.bookable_name);
        Button campusName = findViewById(R.id.campus_name);
        bookableName.setText("Building: " + WordUtils.capitalize(building));
        campusName.setText("Campus: " + WordUtils.capitalize(campus));
    }

    private void initTableView(TableView tableView, List<Bookable> bookables, HashMap<String, List<Booking>> bookings) {
        TableViewAdapter adapter = new TableViewAdapter();
        TableViewListener listener = new TableViewListener(buildingId);
        tableView.setAdapter(adapter);
        tableView.setTableViewListener(listener);
        TableViewModel viewModel = new TableViewModel(bookables, bookings);

        adapter.setAllItems(viewModel.getColumnHeaderList(), viewModel.getRowHeaderList(), viewModel.getCellList());
    }
}