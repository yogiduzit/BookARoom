package com.example.bookaroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BookingForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_booking_form);

    }
}