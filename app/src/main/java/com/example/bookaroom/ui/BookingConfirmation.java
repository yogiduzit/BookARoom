package com.example.bookaroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bookaroom.R;

public class BookingConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_booking_confirmation);
    }
}