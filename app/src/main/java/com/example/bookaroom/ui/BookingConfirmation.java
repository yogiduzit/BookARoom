package com.example.bookaroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bookaroom.R;

public class BookingConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);
    }

    public void onMyBookings(View view) {
        Intent intent = new Intent(this, MyBookingsActivity.class);
        startActivity(intent);
    }
}