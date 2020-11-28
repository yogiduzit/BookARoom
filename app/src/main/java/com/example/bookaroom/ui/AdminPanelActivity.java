package com.example.bookaroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookaroom.AdminPanel;
import com.example.bookaroom.R;

public class AdminPanelActivity extends AppCompatActivity {

    public static final int DAY_START_TIME = 6;
    public static final int DAY_END_TIME = 20;
    public static final double BASE_BOOKING_INTERVAL = 0.5;

    Button submitBtn;
    EditText dayStartTime, dayEndTime, baseBookingInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        dayStartTime = findViewById(R.id.day_start_input);
        dayEndTime = findViewById(R.id.day_end_input);
        baseBookingInterval = findViewById(R.id.base_interval_input);
        submitBtn = findViewById(R.id.submitButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminPanelActivity.this, "Modified Admin Panel successfully!", Toast.LENGTH_LONG).show();
                AdminPanel.DAY_START_TIME = Integer.parseInt(dayStartTime.getText().toString().trim());
                AdminPanel.DAY_END_TIME = Integer.parseInt(dayEndTime.getText().toString().trim());
                AdminPanel.BASE_BOOKING_INTERVAL = Double.parseDouble(baseBookingInterval.getText().toString().trim());
                dayStartTime.setText("");
                dayEndTime.setText("");
                baseBookingInterval.setText("");
            }
        });
    }
}