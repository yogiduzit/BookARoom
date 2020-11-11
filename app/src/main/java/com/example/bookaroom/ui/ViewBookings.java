package com.example.bookaroom.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bookaroom.R;
import com.example.bookaroom.data.database.entity.Building;
import com.example.bookaroom.ui.viewModel.ViewBookingViewModel;

import java.util.List;


public class ViewBookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        ViewBookingViewModel viewModel = new ViewModelProvider(this).get(ViewBookingViewModel.class);
        viewModel.getBuildings().observe(this, new Observer<List<Building>>() {
            @Override
            public void onChanged(List<Building> buildings) {
                for(Building building: buildings) {
                    System.out.println(building.toString());
                }
            }
        });

    }
}