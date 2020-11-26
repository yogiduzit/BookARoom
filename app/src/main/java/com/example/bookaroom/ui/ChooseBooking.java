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
import com.example.bookaroom.ui.adapter.CampusAdapter;
import com.example.bookaroom.ui.tableView.TableViewAdapter;
import com.example.bookaroom.ui.tableView.TableViewModel;
import com.example.bookaroom.ui.viewModel.BookablesViewModel;

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
        bookablesViewModel.getBookables(campusId, buildingId).observe(this, bookables -> {
            if (bookables.isEmpty()) {
                bookingsTableView.setVisibility(View.GONE);
                return;
            }
            initTableView(bookingsTableView, bookables);
        });

        setupHeadings(campusId, buildingId);
    }

    public void onBookingSelected(View view) {
        Intent bookingDetails = new Intent(this, BookingForm.class);
        startActivity(bookingDetails);
    }

    private void setupHeadings(String campus, String building) {
        TextView bookableName = findViewById(R.id.bookable_name);
        bookableName.setText(campus + " > " + building);
    }

    private void initTableView(TableView tableView, List<Bookable> bookables) {
        TableViewAdapter adapter = new TableViewAdapter();
        tableView.setAdapter(adapter);
        TableViewModel viewModel = new TableViewModel(bookables);

        adapter.setAllItems(viewModel.getColumnHeaderList(), viewModel.getRowHeaderList(), viewModel.getCellList());
    }
}