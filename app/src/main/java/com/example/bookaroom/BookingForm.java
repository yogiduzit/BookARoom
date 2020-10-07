package com.example.bookaroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BookingForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_booking_form);

    }

    public void onBookingConfirmed(View view) {
        Intent intent = new Intent(this, BookingConfirmation.class);
        startActivity(intent);
    }
}